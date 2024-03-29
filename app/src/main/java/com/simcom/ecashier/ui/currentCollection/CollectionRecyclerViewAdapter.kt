package com.simcom.ecashier.ui.currentCollection

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.simcom.ecashier.R
import com.simcom.ecashier.model.room.PersonExtended

class CollectionRecyclerViewAdapter(viewLifecycleOwner: LifecycleOwner, private val viewModel: CurrentCollectionViewModel, private val collectionId: Long) : RecyclerView.Adapter<CollectionRecyclerViewAdapter.ViewHolder>() {
    private var people: List<PersonExtended> = ArrayList()

    init {
        viewModel.getCollectionPeople(collectionId).observe(viewLifecycleOwner){
            Log.d("CollectionRecyclerView", "Updating View")
            if(people.size == it.size){
                Log.d("CollectionRecyclerView", "Running smooth view update")

                val changedItems = ArrayList<Int>()
                people.forEachIndexed{index, item->
                    if(item !== it[index]){
                        changedItems.add(index)
                    }
                }
                people = it
                changedItems.forEach{ position ->
                    notifyItemChanged(position)
                }
            }else {
                people = it
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val order: TextView = itemView.findViewById(R.id.order)
        val name: TextView = itemView.findViewById(R.id.name)
        val tick: CheckBox = itemView.findViewById(R.id.checkbox)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPerson = people[position]
        holder.name.text = currentPerson.person.name
        holder.tick.setOnCheckedChangeListener(null)
        holder.tick.isChecked = currentPerson.hasPaid
        holder.tick.setOnCheckedChangeListener{ _, isChecked->
            if(isChecked!=currentPerson.hasPaid) {
                viewModel.setPersonPaid(isChecked, currentPerson.person.id, collectionId)
            }
        }
    }

    override fun getItemCount(): Int {
        return people.size
    }
}