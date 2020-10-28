package com.simcom.ecashier.ui.addCollection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.simcom.ecashier.R;
import com.simcom.ecashier.model.room.Collection;


public class LoadingFragment extends Fragment {

    private AddCollectionViewModel addCollectionViewModel;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_loading, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        addCollectionViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AddCollectionViewModel.class);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addCollectionViewModel.getCurrentCollection().observe(getViewLifecycleOwner(), new Observer<Collection>() {
            @Override
            public void onChanged(Collection collection) {
                navController.navigate(R.id.action_loadingFragment_to_nav_current);
            }
        });
        addCollectionViewModel.finish();
    }
}
