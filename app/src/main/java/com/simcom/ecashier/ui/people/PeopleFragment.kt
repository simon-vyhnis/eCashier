package com.simcom.ecashier.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.simcom.ecashier.R

class PeopleFragment : Fragment() {
    private lateinit var viewModel : PeopleViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_people, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(PeopleViewModel::class.java)
        viewModel.getPeople()?.observe(viewLifecycleOwner) {
            val recyclerView = root.findViewById<RecyclerView>(R.id.groups)
            recyclerView.adapter = ManagePeopleRecyclerViewAdapter(it, viewModel)
            recyclerView.layoutManager = LinearLayoutManager(context)
            if(it.isEmpty()){
                root.findViewById<TextView>(R.id.empty_message).visibility = View.VISIBLE
            }
        }

        root.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_people_detail_to_addPersonFragment)
        }
        return root
    }
}