package com.simcom.ecashier.ui.addCollection

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.simcom.ecashier.R
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.cardview.widget.CardView
import android.view.View
import com.simcom.ecashier.model.room.*
import java.util.ArrayList

class SelectGroupRecyclerViewAdapter( private var groups: List<Group>, private val viewModel : AddCollectionViewModel) : RecyclerView.Adapter<SelectGroupRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentGroup = groups[position]
        holder.name.text = currentGroup.name
        if(currentGroup.id == viewModel.groupId){
            holder.card.setCardBackgroundColor(
                Color.rgb(236, 244, 220)
            )
        }else{
            holder.card.setCardBackgroundColor(Color.WHITE)
        }
        holder.card.setOnClickListener {
            viewModel.groupId = currentGroup.id
            notifyDataSetChanged()
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