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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.simcom.ecashier.R

class EditGroupFragment : Fragment() {
    private lateinit var viewModel : PeopleViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_add_people, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(PeopleViewModel::class.java)
        root.findViewById<TextView>(R.id.text).text = getString(R.string.group_name)
        val editText = root.findViewById<EditText>(R.id.input)
        editText.setText(viewModel.selectedGroup?.name)
        val button = root.findViewById<Button>(R.id.btn)
        button.text = getString(R.string.rename_group)
        button.setOnClickListener {
            if(editText.text.isNotEmpty()){
                viewModel.editGroup(editText.text.toString())
                findNavController().navigate(R.id.action_editGroupFragment_to_nav_people)
            }else{
                Toast.makeText(context, "Enter a name", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }
}