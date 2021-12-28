package com.simcom.ecashier.ui.currentCollection

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simcom.ecashier.R
import com.simcom.ecashier.model.room.CollectionInfo

class CurrentCollectionFragment : Fragment() {
    private lateinit var viewModel : CurrentCollectionViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(requireActivity()).get(CurrentCollectionViewModel::class.java)

        val root: View = inflater.inflate(R.layout.fragment_current_collection, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view)
        val priceText: TextView = root.findViewById(R.id.price)
        val currentText: TextView = root.findViewById(R.id.current)
        val leftText: TextView = root.findViewById(R.id.left)
        val totalText: TextView = root.findViewById(R.id.total)

        viewModel.getCurrentCollectionInfo()
            .observe(this.viewLifecycleOwner) { collectionInfo: CollectionInfo? ->
                if (collectionInfo != null) {
                    priceText.text = getString(R.string.current_price, collectionInfo.price)
                    currentText.text = getString(R.string.current_current, collectionInfo.currentlyCollected)
                    leftText.text = getString(R.string.current_left, collectionInfo.moneyLeft)
                    totalText.text = getString(R.string.current_total, collectionInfo.totalPrice)
                    Log.d("CurrentCollectionFr", "db has changed")
                    if(recyclerView.adapter == null) {
                        recyclerView.adapter = CollectionRecyclerViewAdapter(
                            viewLifecycleOwner,
                            viewModel,
                            collectionInfo.collectionId
                        )
                        recyclerView.layoutManager = LinearLayoutManager(context)
                    }

                } else {
                    priceText.text = getString(R.string.no_collections)
                    currentText.text = " "
                    leftText.text = " "
                    totalText.text = " "
                }
            }
        return root
    }
}