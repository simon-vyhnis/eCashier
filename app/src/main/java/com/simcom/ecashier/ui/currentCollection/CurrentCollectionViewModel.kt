package com.simcom.ecashier.ui.currentCollection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.simcom.ecashier.model.room.CashierDatabase
import com.simcom.ecashier.model.room.CollectionInfo
import com.simcom.ecashier.model.room.PersonExtended
import kotlinx.coroutines.launch

class CurrentCollectionViewModel(application: Application) : AndroidViewModel(application) {
    private var db:CashierDatabase = Room.databaseBuilder(
        application,
        CashierDatabase::class.java, "cashier_database"
    ).fallbackToDestructiveMigration().build()

    fun getCurrentCollectionInfo(): LiveData<CollectionInfo>{
        return db.collectionDao().getCurrentCollectionInfo()
    }

    fun getCollectionPeople(collectionId: Long): LiveData<List<PersonExtended>>{
        return db.personToCollectionDao().getPeopleFromCollection(collectionId)
    }

    fun setPersonPaid(hasPaid: Boolean, personId: Long, collectionId: Long) = viewModelScope.launch{
        db.personToCollectionDao().setPersonPaid(hasPaid, System.currentTimeMillis(), personId, collectionId)
    }

}