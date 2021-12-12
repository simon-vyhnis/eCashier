package com.simcom.ecashier.ui.addCollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.simcom.ecashier.R

class AddCollectionFragment : Fragment() {
    private var addCollectionViewModel: AddCollectionViewModel? = null
    private lateinit var editText: EditText
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root: View = inflater.inflate(R.layout.fragment_add_collection, container, false)
        addCollectionViewModel = ViewModelProvider(
            requireActivity(), ViewModelProvider.AndroidViewModelFactory(
                requireActivity().application
            )
        ).get(
            AddCollectionViewModel::class.java
        )
        val navController: NavController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        editText = root.findViewById<EditText>(R.id.editText)
        editText.setText(addCollectionViewModel.getName())
        root.findViewById<View>(R.id.next_button).setOnClickListener { view: View? ->
            if (editText.getText().toString().isEmpty()) {
                editText.setError("Can't be empty")
            } else {
                addCollectionViewModel.setName(editText.getText().toString())
                navController.navigate(R.id.action_nav_add_to_selectGroupFragment)
            }
        }
        return root
    }
}