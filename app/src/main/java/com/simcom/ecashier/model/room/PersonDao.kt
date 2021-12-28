package com.simcom.ecashier.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDao {
    @Insert
    suspend fun insertPerson(person: Person) : Long

    @Update
    suspend fun editPerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query(
        "SELECT * FROM PERSON_TABLE " +
                "ORDER BY NAME"
    )
    fun getAllPeople(): LiveData<List<Person>>

    @Query(
        "SELECT PERSON_TABLE.name, PERSON_TABLE.id FROM PERSON_TABLE " +
                "JOIN PERSON_TO_GROUP_TABLE ON PERSON_TABLE.id = PERSON_TO_GROUP_TABLE.personId " +
                "WHERE PERSON_TO_GROUP_TABLE.groupId = :groupId " +
                "ORDER BY PERSON_TABLE.name"
    )
    fun getPeopleFromGroup(groupId: Int): LiveData<List<Person>>
}