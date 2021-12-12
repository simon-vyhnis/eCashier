package com.simcom.ecashier.model.repositories

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
import com.simcom.ecashier.model.room.Collection

class Repository(application: Application) {
    private val personDao: PersonDao?
    private val groupDao: GroupDao?
    private val personToGroupDao: PersonToGroupDao?
    private val collectionDao: CollectionDao?
    private val collectionLogDao: CollectionLogDao?
    private val application: Application

    //Person table
    val allPeople: LiveData<List<Person>>
        get() = personDao.getAllPeople()

    fun getPeopleFromGroup(groupId: Int): LiveData<List<Person?>?>? {
        return personDao!!.getPeopleFromGroup(groupId)
    }

    fun createPerson(person: Person?) {
        personDao!!.insertPerson(person)
    }

    //Group table
    fun createGroup(group: Group?) {
        groupDao!!.insert(group)
    }

    fun updateGroup(group: Group?) {
        groupDao!!.update(group)
    }

    fun deleteGroup(group: Group?) {
        groupDao!!.delete(group)
    }

    val allGroups: LiveData<List<Group?>?>?
        get() = groupDao.getAllGroups()

    //PersonToGroup table
    fun addPersonToGroup(person: Person, group: Group) {
        personToGroupDao!!.insert(PersonToGroup(person.id, group.id))
    }

    fun addPersonToGroup(personToGroup: PersonToGroup?) {
        personToGroupDao!!.insert(personToGroup)
    }

    fun removePersonFromGroup(person: Person, group: Group) {
        personToGroupDao!!.delete(PersonToGroup(person.id, group.id))
    }

    fun removePersonFromGroup(personToGroup: PersonToGroup?) {
        val thread = Thread { personToGroupDao!!.delete(personToGroup) }
        thread.start()
    }

    fun getGroupSize(groupId: Int): Int {
        return personToGroupDao!!.getGroupSize(groupId)
    }

    //Collection table
    fun addCollection(collection: Collection?) {
        CashierDatabase.Companion.databaseWriteExecutor.execute(Runnable {
            val oldCollection = collectionDao.getCurrentCollectionClear()
            if (oldCollection != null) {
                oldCollection.isCurrent = false
                collectionDao!!.update(oldCollection)
            }
            collectionDao!!.insert(collection)
        })
    }

    val currentCollection: LiveData<Collection>
        get() = collectionDao.getCurrentCollection()
    val currentCollectionInfo: LiveData<CollectionInfo?>?
        get() = collectionDao.getCurrentCollectionInfo()

    //CollectionLog table
    fun insertCollectionLog(collectionLog: CollectionLog?) {
        CashierDatabase.Companion.databaseWriteExecutor.execute(Runnable {
            collectionLogDao!!.insert(
                collectionLog
            )
        })
    }

    init {
        val database: CashierDatabase = CashierDatabase.Companion.getInstance(application)
        personDao = database.personDao()
        groupDao = database.groupDao()
        personToGroupDao = database.personToGroupDao()
        collectionDao = database.collectionDao()
        collectionLogDao = database.collectionLogDao()
        this.application = application
    }
}