package com.simcom.ecashier.ui.addCollection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.simcom.ecashier.model.room.CashierDatabase
import com.simcom.ecashier.model.room.Collection
import com.simcom.ecashier.model.room.Group
import com.simcom.ecashier.model.room.PersonToCollection
import kotlinx.coroutines.launch

class AddCollectionViewModel(application: Application) : AndroidViewModel(application) {
    var name: String? = null
    var groupId: Long? = null
    var price: Int? = null

    private var db:CashierDatabase = Room.databaseBuilder(
        application,
        CashierDatabase::class.java, "cashier_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    fun createCollection() = viewModelScope.launch{
        if(name != null && groupId != null && price != null){
            val collection = Collection(name!!, groupId!!, price!!, System.currentTimeMillis(), true)
            db.collectionDao().makeNoCollectionCurrent()
            val collectionId = db.collectionDao().insert(collection)
            for(person in db.personToGroupDao().getPeopleFromGroupSynchronous(groupId!!)){
                db.personToCollectionDao().insert(PersonToCollection(collectionId, person.id, false, null))
            }
        }
    }

    fun getGroups() : LiveData<List<Group>> {
        return db.groupDao().allGroups()
    }


}