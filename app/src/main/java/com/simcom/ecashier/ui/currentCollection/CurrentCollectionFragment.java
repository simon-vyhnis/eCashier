package com.simcom.ecashier.ui.currentCollection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.simcom.ecashier.R;
import com.simcom.ecashier.ui.addCollection.AddCollectionViewModel;

public class CurrentCollectionFragment extends Fragment {

    private CurrentCollectionViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(CurrentCollectionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_current_collection, container, false);
        root.findViewById(R.id.recycler_view);

        return root;
    }
}