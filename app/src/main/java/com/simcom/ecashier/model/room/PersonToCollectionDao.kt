package com.simcom.ecashier.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonToCollectionDao {
    @Insert
    suspend fun insert(personToCollection: PersonToCollection)

    @Query("SELECT COUNT(*) FROM PERSON_TO_COLLECTION_TABLE WHERE collectionId = :collectionId AND hasPaid = 1")
    fun getPaymentsCount(collectionId: Int): Int

    @Query("SELECT hasPaid, PERSON_TABLE.* FROM PERSON_TO_COLLECTION_TABLE " +
            "INNER JOIN PERSON_TABLE ON PERSON_TABLE.id = PERSON_TO_COLLECTION_TABLE.personId " +
            "WHERE collectionId = :collectionId " +
            "ORDER BY PERSON_TABLE.name")
    fun getPeopleFromCollection(collectionId: Long) : LiveData<List<PersonExtended>>

    @Query("UPDATE PERSON_TO_COLLECTION_TABLE " +
            "SET hasPaid = :hasPaid, time = :time " +
            "WHERE personId = :personId AND collectionId = :collectionId")
    suspend fun setPersonPaid(hasPaid: Boolean, time: Long, personId: Long, collectionId: Long)
}