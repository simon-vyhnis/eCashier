package com.simcom.ecashier.ui.history;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.simcom.ecashier.model.repositories.Repository;

public class HistoryViewModel extends AndroidViewModel {


    public HistoryViewModel(Application application) {
        super(application);
        Repository repository = new Repository(application);
    }


}