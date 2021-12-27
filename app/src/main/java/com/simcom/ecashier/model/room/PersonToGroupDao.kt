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
interface PersonToGroupDao {
    @Insert
    suspend fun insert(item: PersonToGroup)

    @Delete
    suspend fun delete(item: PersonToGroup)

    @Query("DELETE FROM person_to_group_table WHERE personId = :personId AND groupId = :groupId")
    suspend fun deleteWithIds(personId: Long, groupId: Long)

    @Query("DELETE FROM person_to_group_table WHERE groupId = :groupId")
    suspend fun deleteWithGroupId(groupId: Long)

    @Query(
        "SELECT p.*, cl.collectionId, (SELECT count(*) FROM COLLECTION_LOG_TABLE ORDER BY time) AS rank " +
                "FROM PERSON_TO_GROUP_TABLE AS ptg " +
                "INNER JOIN PERSON_TABLE AS p ON ptg.personId = p.id " +
                "LEFT OUTER JOIN COLLECTION_LOG_TABLE AS cl ON p.id = cl.personId & cl.collectionId = :collectionId " +
                "WHERE ptg.groupId = :groupId " +
                "ORDER BY p.name "
    )
    fun getPeopleByCollection(collectionId: Int, groupId: Int): LiveData<List<PersonExtended>>

    @Query("SELECT PERSON_TABLE.* " +
            "FROM person_to_group_table " +
            "INNER JOIN PERSON_TABLE ON PERSON_TABLE.id = PERSON_TO_GROUP_TABLE.personId " +
            "WHERE groupId = :groupId")
    fun getPeopleFromGroup(groupId: Long) : LiveData<List<Person>>

    @Query("SELECT COUNT(*) FROM PERSON_TO_GROUP_TABLE WHERE groupId = :groupId")
    fun getGroupSize(groupId: Long): Int
}