package com.simcom.ecashier.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CollectionDao {
    @Insert
    public suspend fun insert(collection: Collection) : Long

    @Update
    public suspend fun update(collection: Collection)

    @Delete
    public suspend fun delete(collection: Collection)

    @Query("SELECT * FROM COLLECTION_TABLE where id = :id")
    public fun getCollection(id: Int): LiveData<Collection>

    @Query("SELECT * FROM COLLECTION_TABLE ORDER BY time DESC")
    public fun getAllCollections(): LiveData<List<Collection>>

    @Query("SELECT * FROM COLLECTION_TABLE WHERE isCurrent = 1 LIMIT 1")
    public fun getCurrentCollection(): LiveData<Collection>

    @Query("SELECT * FROM COLLECTION_TABLE WHERE isCurrent = 1 LIMIT 1")
    public fun getCurrentCollectionClear(): Collection

    @Query(
        "SELECT id AS collectionId, price, name, groupId, " +
                "(SELECT COUNT(*) FROM PERSON_TO_COLLECTION_TABLE AS ptc WHERE ptc.collectionId = c.id) AS peopleCount, " +
                "(SELECT COUNT(*) FROM PERSON_TO_COLLECTION_TABLE AS ptc WHERE ptc.collectionId = c.id AND ptc.hasPaid = 1) AS paymentsCount " +
                "FROM COLLECTION_TABLE AS c WHERE c.isCurrent = 1 LIMIT 1")
    public fun getCurrentCollectionInfo(): LiveData<CollectionInfo>

    @Query("UPDATE COLLECTION_TABLE SET isCurrent = 0")
    suspend fun makeNoCollectionCurrent()
}