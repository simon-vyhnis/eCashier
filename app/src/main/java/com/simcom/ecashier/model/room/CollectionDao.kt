package com.simcom.ecashier.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface CollectionDao {

    @Insert
    void insert(Collection collection);

    @Update
    void update(Collection collection);

    @Delete
    void delete(Collection collection);

    @Query("SELECT * FROM COLLECTION_TABLE where id = :id")
    LiveData<Collection> getCollection(int id);

    @Query("SELECT * FROM COLLECTION_TABLE ORDER BY time DESC")
    LiveData<List<Collection>> getAllCollections();

    @Query("SELECT * FROM COLLECTION_TABLE WHERE current = 1 LIMIT 1")
    LiveData<Collection> getCurrentCollection();


    @Query("SELECT * FROM COLLECTION_TABLE WHERE current = 1 LIMIT 1")
    Collection getCurrentCollectionClear();

    @Query("SELECT id AS collectionId, price, name, groupId, " +
            "(SELECT COUNT(*) FROM PERSON_TO_GROUP_TABLE AS ptg WHERE c.groupId = ptg.groupId) AS peopleCount, " +
            "(SELECT COUNT(*) FROM COLLECTION_LOG_TABLE AS cl WHERE cl.collectionId = c.id) AS paymentsCount " +
            "FROM COLLECTION_TABLE AS c WHERE c.current = 1 LIMIT 1")
    LiveData<CollectionInfo> getCurrentCollectionInfo();

}
