package com.simcom.ecashier.ui.currentCollection

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.simcom.ecashier.model.room.*

class CurrentCollectionViewModel(application: Application) : AndroidViewModel(application) {
    private var db:CashierDatabase = Room.databaseBuilder(
        application,
        CashierDatabase::class.java, "cashier_database"
    ).fallbackToDestructiveMigration().build()

    fun getCurrentCollectionInfo(): LiveData<CollectionInfo>{
        return db.collectionDao().getCurrentCollectionInfo()
    }

}