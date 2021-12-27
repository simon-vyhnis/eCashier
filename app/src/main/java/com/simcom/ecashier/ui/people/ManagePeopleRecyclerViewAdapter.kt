package com.simcom.ecashier.ui.people

import android.view.*
import com.simcom.ecashier.R
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.cardview.widget.CardView
import android.widget.ImageButton
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import com.simcom.ecashier.model.room.*

class ManagePeopleRecyclerViewAdapter(private val people: List<Person>, private val viewModel: PeopleViewModel) : RecyclerView.Adapter<ManagePeopleRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person_edit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = people[position].name
        holder.itemView.findViewById<ImageButton>(R.id.button_menu).setOnClickListener{
            val popup = PopupMenu(it.context, it)
            popup.setOnMenuItemClickListener { item ->
                return@setOnMenuItemClickListener when (item.itemId) {
                    R.id.delete -> {
                        viewModel.deletePerson(people[position])
                        notifyItemRemoved(position)
                        true
                    }
                    R.id.edit -> {
                        viewModel.selectedPerson = people[position]
                        it.findNavController().navigate(R.id.action_people_detail_to_editPersonFragment)
                        true
                    }
                    else -> false
                }
            }
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.item_actions, popup.menu)
            popup.show()
        }
    }

    override fun getItemCount(): Int {
        return people.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.findViewById(R.id.name)
        var card: CardView = itemView.findViewById(R.id.card)

    }
}