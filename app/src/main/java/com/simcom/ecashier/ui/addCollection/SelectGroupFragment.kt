package com.simcom.ecashier.ui.addCollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simcom.ecashier.R

class SelectGroupFragment : Fragment() {
    private lateinit var viewModel : AddCollectionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_select_group, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(AddCollectionViewModel::class.java)
        root.findViewById<View>(R.id.next_button).setOnClickListener {
            if(viewModel.groupId != null) {
                findNavController().navigate(R.id.action_selectGroupFragment_to_priceFragment)
            }else{
                Toast.makeText(context, "Select a group", Toast.LENGTH_SHORT).show()
            }
        }
        root.findViewById<View>(R.id.back_button)
            .setOnClickListener { findNavController().navigate(R.id.action_selectGroupFragment_to_nav_add) }

        val recyclerView = root.findViewById<RecyclerView>(R.id.groups)
        viewModel.getGroups().observe(viewLifecycleOwner) {
            recyclerView.adapter = SelectGroupRecyclerViewAdapter(it, viewModel)
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        return root
    }
}