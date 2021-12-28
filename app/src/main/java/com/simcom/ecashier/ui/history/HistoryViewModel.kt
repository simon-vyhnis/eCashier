package com.simcom.ecashier.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.simcom.ecashier.model.room.CashierDatabase
import com.simcom.ecashier.model.room.Collection

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private var db: CashierDatabase = Room.databaseBuilder(
        application,
        CashierDatabase::class.java, "cashier_database"
    ).fallbackToDestructiveMigration().build()

    fun getAllCollections() : LiveData<List<Collection>> {
        return db.collectionDao().getAllCollections()
    }
}