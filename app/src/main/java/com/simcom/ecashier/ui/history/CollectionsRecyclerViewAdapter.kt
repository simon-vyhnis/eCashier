package com.simcom.ecashier.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simcom.ecashier.R
import com.simcom.ecashier.model.room.Collection

class CollectionsRecyclerViewAdapter(private var collections: List<Collection>) :
    RecyclerView.Adapter<CollectionsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.name).text = collections[position].name
    }
    override fun getItemCount(): Int {
        return collections.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}