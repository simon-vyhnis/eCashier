package com.simcom.ecashier.model.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.simcom.ecashier.model.room.CashierDatabase;
import com.simcom.ecashier.model.room.Collection;
import com.simcom.ecashier.model.room.CollectionDao;
import com.simcom.ecashier.model.room.CollectionInfo;
import com.simcom.ecashier.model.room.CollectionLog;
import com.simcom.ecashier.model.room.CollectionLogDao;
import com.simcom.ecashier.model.room.Group;
import com.simcom.ecashier.model.room.GroupDao;
import com.simcom.ecashier.model.room.Person;
import com.simcom.ecashier.model.room.PersonDao;
import com.simcom.ecashier.model.room.PersonToGroup;
import com.simcom.ecashier.model.room.PersonToGroupDao;

import java.util.List;


public class Repository {
    private PersonDao personDao;
    private GroupDao groupDao;
    private PersonToGroupDao personToGroupDao;
    private CollectionDao collectionDao;
    private CollectionLogDao collectionLogDao;
    private Application application;

    public Repository(Application application){
        CashierDatabase database = CashierDatabase.getInstance(application);
        personDao = database.personDao();
        groupDao = database.groupDao();
        personToGroupDao = database.personToGroupDao();
        collectionDao = database.collectionDao();
        collectionLogDao = database.collectionLogDao();
        this.application = application;
    }

    //Person table
    public LiveData<List<Person>> getAllPeople(){
        return personDao.getAllPeople();
    }
    public LiveData<List<Person>> getPeopleFromGroup(int groupId){
        return personDao.getPeopleFromGroup(groupId);
    }
    public void createPerson(Person person){
        personDao.insertPerson(person);
    }

    //Group table
    public void createGroup(Group group){
        groupDao.insert(group);
    }
    public void updateGroup(Group group){
        groupDao.update(group);
    }
    public void deleteGroup(Group group){
        groupDao.delete(group);
    }
    public LiveData<List<Group>> getAllGroups(){
        return groupDao.getAllGroups();
    }

    //PersonToGroup table
    public void addPersonToGroup(Person person, Group group){
        personToGroupDao.insert(new PersonToGroup(person.getId(),group.getId()));
    }
    public void addPersonToGroup(PersonToGroup personToGroup){
        personToGroupDao.insert(personToGroup);
    }
    public void removePersonFromGroup(Person person, Group group){
        personToGroupDao.delete(new PersonToGroup(person.getId(),group.getId()));
    }
    public void removePersonFromGroup(final PersonToGroup personToGroup){
        Thread thread = new Thread(() -> personToGroupDao.delete(personToGroup));
        thread.start();
    }
    public int getGroupSize(int groupId){
        return personToGroupDao.getGroupSize(groupId);
    }

    //Collection table
    public void addCollection(Collection collection){
        CashierDatabase.databaseWriteExecutor.execute(()->{
            Collection oldCollection = collectionDao.getCurrentCollectionClear();
            if(oldCollection!=null){
                oldCollection.setCurrent(false);
                collectionDao.update(oldCollection);
            }
            collectionDao.insert(collection);
        });
    }

    public LiveData<Collection> getCurrentCollection(){
        return collectionDao.getCurrentCollection();
    }

    public LiveData<CollectionInfo> getCurrentCollectionInfo(){
        return collectionDao.getCurrentCollectionInfo();
    }

    //CollectionLog table
    public void insertCollectionLog(CollectionLog collectionLog){
        CashierDatabase.databaseWriteExecutor.execute(()->collectionLogDao.insert(collectionLog));
    }
}

