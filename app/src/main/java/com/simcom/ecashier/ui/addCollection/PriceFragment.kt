package com.simcom.ecashier.ui.addCollection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.simcom.ecashier.R;


public class PriceFragment extends Fragment {

    private AddCollectionViewModel addCollectionViewModel;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_price, container, false);
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        addCollectionViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AddCollectionViewModel.class);

        editText = root.findViewById(R.id.editText);
        if(addCollectionViewModel.getPrice()!=0){
            editText.setText(String.valueOf(addCollectionViewModel.getPrice()));
        }
        root.findViewById(R.id.finish_button).setOnClickListener(view -> {
            if(editText.getText().toString().isEmpty()){
                editText.setError("Can't be empty");
            }else {
                try {
                    addCollectionViewModel.setPrice(Integer.parseInt(editText.getText().toString()));
                } catch (NumberFormatException nfe) {

                    Toast.makeText(getActivity().getApplicationContext(), "Error NFE!", Toast.LENGTH_SHORT).show();
                    nfe.printStackTrace();
                }

                navController.navigate(R.id.action_priceFragment_to_loadingFragment);
            }
        });
        root.findViewById(R.id.back_button).setOnClickListener(view -> {
            if(!editText.getText().toString().isEmpty()){
                addCollectionViewModel.setPrice(Integer.parseInt(editText.getText().toString()));
            }
            navController.navigate(R.id.action_priceFragment_to_selectGroupFragment);
        });
        return root;
    }
}
