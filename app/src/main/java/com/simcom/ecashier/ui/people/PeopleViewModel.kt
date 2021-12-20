package com.simcom.ecashier.ui.people

import android.app.Application
import com.simcom.ecashier.ui.people.PeopleViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.simcom.ecashier.R
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.ViewModel
import com.simcom.ecashier.ui.history.HistoryViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.AndroidViewModel
import com.simcom.ecashier.ui.addCollection.AddCollectionViewModel
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.simcom.ecashier.ui.currentCollection.CurrentCollectionViewModel
import com.simcom.ecashier.ui.addCollection.ErrorDialog
import android.os.Build
import android.graphics.drawable.ColorDrawable
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Database
import androidx.room.RoomDatabase
import kotlin.jvm.Synchronized
import androidx.room.Room
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.NavigationUI
import com.simcom.ecashier.model.room.*

class PeopleViewModel(application:Application) : AndroidViewModel(application) {
    private var db: CashierDatabase = Room.databaseBuilder(
        application,
        CashierDatabase::class.java, "cashier_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    var selectedGroup: Group? = null

    fun getGroups() : LiveData<List<Group>>{
        return db.groupDao().allGroups()
    }

    fun getPeople() : LiveData<List<Person>>? {
        return selectedGroup?.let { db.personToGroupDao().getPeopleFromGroup(it.id) }
    }

}