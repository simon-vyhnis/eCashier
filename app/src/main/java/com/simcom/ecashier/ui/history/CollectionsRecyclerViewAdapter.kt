package com.simcom.ecashier.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import com.simcom.ecashier.R
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.simcom.ecashier.model.room.Collection
import java.util.ArrayList

class CollectionsRecyclerViewAdapter :
    RecyclerView.Adapter<CollectionsRecyclerViewAdapter.ViewHolder>() {
    private var collections: List<Collection> = ArrayList()
    fun setCollections(collections: List<Collection>) {
        this.collections = collections
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}
    override fun getItemCount(): Int {
        return collections.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}