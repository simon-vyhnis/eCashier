package com.simcom.ecashier.ui.addCollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.simcom.ecashier.R

class PriceFragment : Fragment() {
    private lateinit var viewModel : AddCollectionViewModel
    private lateinit var editText: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_price, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(AddCollectionViewModel::class.java)
        editText = root.findViewById(R.id.editText)
        root.findViewById<View>(R.id.finish_button).setOnClickListener {
            if (editText.text.toString().isEmpty()) {
                editText.error = "Can't be empty"
            } else {
                try {
                    viewModel.price = editText.text.toString().toInt()
                    viewModel.createCollection()
                    findNavController().navigate(R.id.action_priceFragment_to_nav_current)
                } catch (nfe: NumberFormatException) {
                    Toast.makeText(context, "Error NFE!", Toast.LENGTH_SHORT).show()
                    nfe.printStackTrace()
                }
            }
        }
        root.findViewById<View>(R.id.back_button).setOnClickListener {
            if (editText.text.toString().isNotEmpty()) {
                viewModel.price = editText.text.toString().toInt()
            }
            findNavController().navigate(R.id.action_priceFragment_to_selectGroupFragment)
        }
        return root
    }
}