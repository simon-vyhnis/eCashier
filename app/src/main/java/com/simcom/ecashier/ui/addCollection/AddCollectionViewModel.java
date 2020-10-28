package com.simcom.ecashier.ui.addCollection;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.simcom.ecashier.model.repositories.Repository;
import com.simcom.ecashier.model.room.Collection;


public class AddCollectionViewModel extends AndroidViewModel {

    private String name;
    //Only for testing!
    private int groupId = 1351;
    private int price;

    private Repository repository;

    public AddCollectionViewModel(Application application) {
        super(application);
        repository=new Repository(application);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LiveData<Collection> getCurrentCollection(){
        return repository.getCurrentCollection();
    }
    public void finish(){
        Collection collection = new Collection(name,groupId,price,System.currentTimeMillis(), true);
        repository.addCollection(collection);
    }
}