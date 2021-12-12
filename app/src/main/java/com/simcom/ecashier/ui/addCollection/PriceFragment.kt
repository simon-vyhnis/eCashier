package com.simcom.ecashier.ui.addCollection

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.simcom.ecashier.R
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import android.widget.EditText
import android.widget.Toast
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.simcom.ecashier.ui.history.HistoryViewModel
import java.lang.NumberFormatException

class PriceFragment : Fragment() {
    private val viewModel : AddCollectionViewModel by viewModels()
    private lateinit var editText: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_price, container, false)
        editText = root.findViewById(R.id.editText)
        if (viewModel.getPrice() != 0) {
            editText.setText(viewModel.getPrice().toString())
        }
        root.findViewById<View>(R.id.finish_button).setOnClickListener { view: View? ->
            if (editText.text.toString().isEmpty()) {
                editText.error = "Can't be empty"
            } else {
                try {
                    viewModel.setPrice(editText.getText().toString().toInt())
                } catch (nfe: NumberFormatException) {
                    Toast.makeText(context, "Error NFE!", Toast.LENGTH_SHORT).show()
                    nfe.printStackTrace()
                }
                findNavController().navigate(R.id.action_priceFragment_to_loadingFragment)
            }
        }
        root.findViewById<View>(R.id.back_button).setOnClickListener { view: View? ->
            if (!editText.getText().toString().isEmpty()) {
                viewModel.setPrice(editText.getText().toString().toInt())
            }
            findNavController().navigate(R.id.action_priceFragment_to_selectGroupFragment)
        }
        return root
    }
}