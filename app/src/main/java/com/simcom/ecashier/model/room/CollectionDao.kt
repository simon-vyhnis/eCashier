package com.simcom.ecashier.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CollectionDao {
    @Insert
    suspend fun insert(collection: Collection) : Long

    @Update
    suspend fun update(collection: Collection)

    @Delete
    suspend fun delete(collection: Collection)

    @Query("SELECT * FROM COLLECTION_TABLE where id = :id")
    fun getCollection(id: Int): LiveData<Collection>

    @Query("SELECT * FROM COLLECTION_TABLE ORDER BY time DESC")
    fun getAllCollections(): LiveData<List<Collection>>

    @Query("SELECT * FROM COLLECTION_TABLE WHERE isCurrent = 1 LIMIT 1")
    fun getCurrentCollection(): LiveData<Collection>

    @Query("SELECT * FROM COLLECTION_TABLE WHERE isCurrent = 1 LIMIT 1")
    fun getCurrentCollectionClear(): Collection

    @Query(
        "SELECT id AS collectionId, price, name, groupId, " +
                "(SELECT COUNT(*) FROM PERSON_TO_COLLECTION_TABLE AS ptc WHERE ptc.collectionId = c.id) AS peopleCount, " +
                "(SELECT COUNT(*) FROM PERSON_TO_COLLECTION_TABLE AS ptc WHERE ptc.collectionId = c.id AND ptc.hasPaid = 1) AS paymentsCount " +
                "FROM COLLECTION_TABLE AS c WHERE c.isCurrent = 1 LIMIT 1")
    fun getCurrentCollectionInfo(): LiveData<CollectionInfo>

    @Query("UPDATE COLLECTION_TABLE SET isCurrent = 0")
    suspend fun makeNoCollectionCurrent()
}