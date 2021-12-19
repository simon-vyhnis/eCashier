package com.simcom.ecashier.ui.currentCollection

import android.graphics.Color
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
import androidx.lifecycle.LiveData
import androidx.cardview.widget.CardView
import com.simcom.ecashier.ui.currentCollection.CurrentCollectionViewModel
import com.simcom.ecashier.ui.addCollection.ErrorDialog
import android.os.Build
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.*
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
import java.util.ArrayList

class CollectionRecyclerViewAdapter :
    RecyclerView.Adapter<CollectionRecyclerViewAdapter.ViewHolder>() {
    private var people: List<PersonExtended> = ArrayList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val order: TextView = itemView.findViewById(R.id.order)
        val name: TextView = itemView.findViewById(R.id.name)
        val tick: CheckBox = itemView.findViewById(R.id.checkbox)
        val card: CardView = itemView.findViewById(R.id.card)

    }

    fun setPeople(people: List<PersonExtended>) {
        this.people = people
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPerson = people[position]
        holder.name.text = currentPerson.person.name
        if (currentPerson.hasPaid()) {
            holder.card.setCardBackgroundColor(Color.rgb(236, 244, 220))
            holder.order.setText(currentPerson.rank)
            holder.order.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return people.size
    }
}