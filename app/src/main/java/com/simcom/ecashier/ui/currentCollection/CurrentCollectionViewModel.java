package com.simcom.ecashier.ui.currentCollection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CurrentCollectionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CurrentCollectionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}