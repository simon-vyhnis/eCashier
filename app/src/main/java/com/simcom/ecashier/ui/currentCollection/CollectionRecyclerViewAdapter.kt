package com.simcom.ecashier.ui.currentCollection

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.simcom.ecashier.R
import androidx.recyclerview.widget.RecyclerView
import androidx.cardview.widget.CardView
import android.view.View
import android.widget.*
import com.simcom.ecashier.model.room.PersonExtended
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
        if (currentPerson.hasPaid) {
            holder.card.setCardBackgroundColor(Color.rgb(236, 244, 220))
            holder.order.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return people.size
    }
}