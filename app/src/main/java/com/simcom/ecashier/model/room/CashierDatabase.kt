package com.simcom.ecashier.model.room

import android.content.Context
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
import com.simcom.ecashier.model.room.PersonExtended
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Database
import com.simcom.ecashier.model.room.PersonToGroup
import com.simcom.ecashier.model.room.CollectionLog
import androidx.room.RoomDatabase
import com.simcom.ecashier.model.room.PersonDao
import com.simcom.ecashier.model.room.GroupDao
import com.simcom.ecashier.model.room.PersonToGroupDao
import com.simcom.ecashier.model.room.CollectionDao
import com.simcom.ecashier.model.room.CollectionLogDao
import com.simcom.ecashier.model.room.CashierDatabase
import kotlin.jvm.Synchronized
import androidx.room.Room
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.NavigationUI
import java.util.concurrent.Executors

@Database(
    entities = [Person::class, Group::class, PersonToGroup::class, Collection::class, CollectionLog::class],
    version = 4
)
abstract class CashierDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao?
    abstract fun groupDao(): GroupDao?
    abstract fun personToGroupDao(): PersonToGroupDao?
    abstract fun collectionDao(): CollectionDao?
    abstract fun collectionLogDao(): CollectionLogDao?
}