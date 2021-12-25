package com.simcom.ecashier.ui.addCollection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.simcom.ecashier.model.room.CashierDatabase
import com.simcom.ecashier.model.room.Collection
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
            db.collectionDao().insert(collection)
        }
    }

}