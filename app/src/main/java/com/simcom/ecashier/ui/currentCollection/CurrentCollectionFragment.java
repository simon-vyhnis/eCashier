package com.simcom.ecashier.ui.currentCollection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.simcom.ecashier.R;
import com.simcom.ecashier.model.room.CollectionInfo;
import com.simcom.ecashier.ui.addCollection.AddCollectionViewModel;

public class CurrentCollectionFragment extends Fragment {

    private CurrentCollectionViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(CurrentCollectionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_current_collection, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        final TextView priceText = root.findViewById(R.id.price);
        final TextView currentText = root.findViewById(R.id.price);
        final TextView leftText = root.findViewById(R.id.price);
        final TextView totalText = root.findViewById(R.id.price);
        viewModel.getCurrentCollectionInfo().observe(this.getViewLifecycleOwner(), new Observer<CollectionInfo>() {
            @Override
            public void onChanged(CollectionInfo collectionInfo) {
                if(collectionInfo!=null) {
                    priceText.setText(String.valueOf(collectionInfo.getPrice()));
                    currentText.setText(String.valueOf(collectionInfo.getCurrentlyCollected()));
                    leftText.setText(String.valueOf(collectionInfo.getMoneyLeft()));
                    totalText.setText(String.valueOf(collectionInfo.getTotalPrice()));
                }
            }
        });
        return root;
    }
}