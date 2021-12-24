package com.simcom.ecashier.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.simcom.ecashier.R

class AddGroupFragment : Fragment() {
    private lateinit var viewModel : PeopleViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_groups, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(PeopleViewModel::class.java)
        root.findViewById<TextView>(R.id.text).text = getString(R.string.group_name)
        val editText = root.findViewById<EditText>(R.id.input)
        editText.hint = "Class 1.A"
        val button = root.findViewById<Button>(R.id.fab)
        button.text = getString(R.string.create_group)
        button.setOnClickListener {
            if(editText.text.isNotEmpty()){
                //TODO: viewModel.addGroup(editText.text)
            }else{
                Toast.makeText(context, "Enter a name", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }
}