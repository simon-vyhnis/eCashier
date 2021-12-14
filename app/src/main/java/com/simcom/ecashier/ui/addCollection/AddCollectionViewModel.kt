package com.simcom.ecashier.ui.addCollection

import android.app.Application
import com.simcom.ecashier.ui.people.PeopleViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.simcom.ecashier.R
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.simcom.ecashier.ui.history.HistoryViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.simcom.ecashier.ui.addCollection.AddCollectionViewModel
import android.widget.EditText
import android.widget.Toast
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.simcom.ecashier.ui.currentCollection.CurrentCollectionViewModel
import com.simcom.ecashier.ui.addCollection.ErrorDialog
import android.os.Build
import android.graphics.drawable.ColorDrawable
import android.util.Log
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
import androidx.lifecycle.*
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.NavigationUI
import com.simcom.ecashier.model.repositories.Repository
import com.simcom.ecashier.model.room.Collection
import kotlinx.coroutines.launch

class AddCollectionViewModel(application: Application) : AndroidViewModel(application) {
    var name: String? = null
    var groupId: Int? = null
    var price: Int? = null

    private var db:CashierDatabase = Room.databaseBuilder(
        application,
        CashierDatabase::class.java, "cashier_database"
    ).build()

    private val repository: Repository = Repository(application)
    fun getCurrentCollection(): LiveData<Collection> {
        return repository.currentCollection
    }

    fun createCollection() = viewModelScope.launch{
        if(name != null && groupId != null && price != null){
            val collection = Collection(name!!, groupId!!, price!!, System.currentTimeMillis(), true)
            db.collectionDao().insert(collection)
        }
    }

}