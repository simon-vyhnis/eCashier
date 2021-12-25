package com.simcom.ecashier.model.room

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
import com.simcom.ecashier.model.room.PersonToGroup
import com.simcom.ecashier.model.room.CollectionLog
import com.simcom.ecashier.model.room.PersonDao
import com.simcom.ecashier.model.room.GroupDao
import com.simcom.ecashier.model.room.PersonToGroupDao
import com.simcom.ecashier.model.room.CollectionDao
import com.simcom.ecashier.model.room.CollectionLogDao
import com.simcom.ecashier.model.room.CashierDatabase
import kotlin.jvm.Synchronized
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.NavigationUI
import androidx.room.*

@Dao
interface PersonDao {
    @Insert
    suspend fun insertPerson(person: Person) : Long

    @Update
    suspend fun editPerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query(
        "SELECT * FROM PERSON_TABLE " +
                "ORDER BY NAME"
    )
    fun getAllPeople(): LiveData<List<Person>>

    @Query(
        "SELECT PERSON_TABLE.name, PERSON_TABLE.id FROM PERSON_TABLE " +
                "JOIN PERSON_TO_GROUP_TABLE ON PERSON_TABLE.id = PERSON_TO_GROUP_TABLE.personId " +
                "WHERE PERSON_TO_GROUP_TABLE.groupId = :groupId " +
                "ORDER BY PERSON_TABLE.name"
    )
    fun getPeopleFromGroup(groupId: Int): LiveData<List<Person>>
}