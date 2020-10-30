package com.simcom.ecashier.ui.addCollection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.simcom.ecashier.R;

public class AddCollectionFragment extends Fragment {

    private AddCollectionViewModel addCollectionViewModel;
    private EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_collection, container, false);
        addCollectionViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AddCollectionViewModel.class);
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        editText = root.findViewById(R.id.editText);
        editText.setText(addCollectionViewModel.getName());

        root.findViewById(R.id.next_button).setOnClickListener(view -> {
            if(editText.getText().toString().isEmpty()){
                editText.setError("Can't be empty");
            }else {
                addCollectionViewModel.setName(editText.getText().toString());
                navController.navigate(R.id.action_nav_add_to_selectGroupFragment);
            }
        });
        return root;
    }
}