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
interface CollectionDao {
    @Insert
    public suspend fun insert(collection: Collection)

    @Update
    public suspend fun update(collection: Collection)

    @Delete
    public suspend fun delete(collection: Collection)

    @Query("SELECT * FROM COLLECTION_TABLE where id = :id")
    public fun getCollection(id: Int): LiveData<Collection>

    @Query("SELECT * FROM COLLECTION_TABLE ORDER BY time DESC")
    public fun getAllCollections(): LiveData<List<Collection>>

    @Query("SELECT * FROM COLLECTION_TABLE WHERE current = 1 LIMIT 1")
    public fun getCurrentCollection(): LiveData<Collection>

    @Query("SELECT * FROM COLLECTION_TABLE WHERE current = 1 LIMIT 1")
    public fun getCurrentCollectionClear(): Collection

    @Query(
        "SELECT id AS collectionId, price, name, groupId, " +
                "(SELECT COUNT(*) FROM PERSON_TO_GROUP_TABLE AS ptg WHERE c.groupId = ptg.groupId) AS peopleCount, " +
                "(SELECT COUNT(*) FROM COLLECTION_LOG_TABLE AS cl WHERE cl.collectionId = c.id) AS paymentsCount " +
                "FROM COLLECTION_TABLE AS c WHERE c.current = 1 LIMIT 1"
    )
    public fun getCurrentCollectionInfo(): LiveData<CollectionInfo>
}