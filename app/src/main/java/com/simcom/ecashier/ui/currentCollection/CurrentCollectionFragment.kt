package com.simcom.ecashier.ui.currentCollection

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.simcom.ecashier.R
import com.simcom.ecashier.model.room.CollectionInfo
import com.simcom.ecashier.ui.addCollection.AddCollectionViewModel
import com.simcom.ecashier.ui.addCollection.ErrorDialog

class CurrentCollectionFragment : Fragment() {
    private val viewModel : AddCollectionViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root: View = inflater.inflate(R.layout.fragment_current_collection, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view)
        val priceText: TextView = root.findViewById(R.id.price)
        val currentText: TextView = root.findViewById(R.id.current)
        val leftText: TextView = root.findViewById(R.id.left)
        val totalText: TextView = root.findViewById(R.id.total)
        viewModel.getCurrentCollectionInfo()
            .observe(this.viewLifecycleOwner) { collectionInfo: CollectionInfo? ->
                if (collectionInfo != null) {
                    //getActivity().getActionBar().setTitle(collectionInfo.getName());
                    priceText.text = getString(R.string.current_price) + collectionInfo.getPrice()
                    currentText.text = getString(R.string.current_current) + collectionInfo.currentlyCollected
                    leftText.text = getString(R.string.current_left) + collectionInfo.moneyLeft
                    totalText.text = getString(R.string.current_total) + collectionInfo.totalPrice
                } else {
                    val errorDialog = ErrorDialog(requireContext())
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        errorDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        errorDialog.create()
                        errorDialog.show()
                    }
                }
            }
        return root
    }
}