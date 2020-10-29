package com.simcom.ecashier.model.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CollectionLogDao {
    @Insert
    void insert(CollectionLog collectionLog);

    @Query("SELECT COUNT(*) FROM COLLECTION_LOG_TABLE WHERE collectionId = :collectionId")
    int getPaymentsCount(int collectionId);
}
