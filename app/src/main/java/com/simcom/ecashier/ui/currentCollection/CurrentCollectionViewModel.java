package com.simcom.ecashier.ui.currentCollection;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.simcom.ecashier.model.repositories.Repository;
import com.simcom.ecashier.model.room.Collection;
import com.simcom.ecashier.model.room.CollectionInfo;

import java.util.List;

public class CurrentCollectionViewModel extends AndroidViewModel {

    private Collection currentCollection;
    private Repository repository;

    public CurrentCollectionViewModel(Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<CollectionInfo> getCurrentCollectionInfo(){
        return repository.getCurrentCollectionInfo();
    }


}