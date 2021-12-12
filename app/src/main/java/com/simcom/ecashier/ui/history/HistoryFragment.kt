package com.simcom.ecashier.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.simcom.ecashier.R

class HistoryFragment : Fragment() {
    private val viewModel : HistoryViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root: View = inflater.inflate(R.layout.fragment_history, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view)
        return root
    }
}