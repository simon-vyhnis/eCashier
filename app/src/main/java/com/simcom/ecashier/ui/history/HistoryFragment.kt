package com.simcom.ecashier.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.simcom.ecashier.R;
import com.simcom.ecashier.ui.addCollection.AddCollectionViewModel;

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_history, container, false);
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        historyViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(HistoryViewModel.class);
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);

        return root;
    }
}