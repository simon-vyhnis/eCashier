package com.simcom.ecashier.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insertPerson(Person person);

    @Update
    void editPerson(Person person);

    @Delete
    void deletePerson(Person person);

    @Query("SELECT * FROM PERSON_TABLE " +
            "ORDER BY NAME")
    LiveData<List<Person>> getAllPeople();

    @Query("SELECT PERSON_TABLE.name, PERSON_TABLE.id FROM PERSON_TABLE " +
            "JOIN PERSON_TO_GROUP_TABLE ON PERSON_TABLE.id = PERSON_TO_GROUP_TABLE.personId " +
            "WHERE PERSON_TO_GROUP_TABLE.groupId = :groupId " +
            "ORDER BY PERSON_TABLE.name")
    LiveData<List<Person>> getPeopleFromGroup(int groupId);
}
