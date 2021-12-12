package com.simcom.ecashier.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonToGroupDao {
    @Insert
    void insert(PersonToGroup item);

    @Delete
    void delete(PersonToGroup item);

    @Query("SELECT p.*, cl.collectionId, (SELECT count(*) FROM COLLECTION_LOG_TABLE ORDER BY time) AS rank " +
            "FROM PERSON_TO_GROUP_TABLE AS ptg " +
            "INNER JOIN PERSON_TABLE AS p ON ptg.personId = p.id " +
            "LEFT OUTER JOIN COLLECTION_LOG_TABLE AS cl ON p.id = cl.personId & cl.collectionId = :collectionId " +
            "WHERE ptg.groupId = :groupId " +
            "ORDER BY p.name ")
    LiveData<List<PersonExtended>> getPeopleByCollection(int collectionId, int groupId);

    @Query("SELECT COUNT(*) FROM PERSON_TO_GROUP_TABLE WHERE groupId = :groupId")
    int getGroupSize(int groupId);
}

