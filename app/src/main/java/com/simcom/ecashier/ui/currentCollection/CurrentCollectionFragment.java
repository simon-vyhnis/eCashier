package com.simcom.ecashier.ui.currentCollection;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
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
import com.simcom.ecashier.ui.addCollection.ErrorDialog;

public class CurrentCollectionFragment extends Fragment {

    private CurrentCollectionViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(CurrentCollectionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_current_collection, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        final TextView priceText = root.findViewById(R.id.price);
        final TextView currentText = root.findViewById(R.id.current);
        final TextView leftText = root.findViewById(R.id.left);
        final TextView totalText = root.findViewById(R.id.total);
        viewModel.getCurrentCollectionInfo().observe(this.getViewLifecycleOwner(), collectionInfo -> {
            if(collectionInfo!=null) {
                //getActivity().getActionBar().setTitle(collectionInfo.getName());
                priceText.setText(getString(R.string.current_price)+collectionInfo.getPrice());
                currentText.setText(getString(R.string.current_current)+collectionInfo.getCurrentlyCollected());
                leftText.setText(getString(R.string.current_left)+collectionInfo.getMoneyLeft());
                totalText.setText(getString(R.string.current_total)+collectionInfo.getTotalPrice());
            }else{
                ErrorDialog errorDialog = new ErrorDialog(getContext());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    errorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    errorDialog.create();
                    errorDialog.show();
                }
            }
        });
        return root;
    }
}