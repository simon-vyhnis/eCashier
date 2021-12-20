package com.simcom.ecashier.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import com.simcom.ecashier.R
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.cardview.widget.CardView
import android.view.View
import androidx.navigation.findNavController
import com.simcom.ecashier.model.room.*

class ManageGroupsRecyclerViewAdapter(private val groups: List<Group>, private val viewModel: PeopleViewModel) : RecyclerView.Adapter<ManageGroupsRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = groups[position].name
        holder.card.setOnClickListener {
            viewModel.selectedGroup = groups[position]
            holder.card.findNavController().navigate(R.id.action_nav_people_to_people_detail)
        }
    }

    override fun getItemCount(): Int {
        return groups.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var card: CardView = itemView.findViewById(R.id.card)

    }
}