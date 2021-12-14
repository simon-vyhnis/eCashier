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
    private lateinit var editText: EditText
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root: View = inflater.inflate(R.layout.fragment_add_collection, container, false)
        val viewModel = ViewModelProvider(requireActivity()).get(AddCollectionViewModel::class.java)
        val navController: NavController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        editText = root.findViewById(R.id.editText)
        root.findViewById<View>(R.id.next_button).setOnClickListener { view: View? ->
            if (editText.text.toString().isEmpty()) {
                editText.error = "Can't be empty"
            } else {
                viewModel.name = editText.text.toString()
                navController.navigate(R.id.action_nav_add_to_selectGroupFragment)
            }
        }
        return root
    }
}