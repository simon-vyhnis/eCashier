package com.simcom.ecashier.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonToGroupDao {
    @Insert
    suspend fun insert(item: PersonToGroup)

    @Delete
    suspend fun delete(item: PersonToGroup)

    @Query("DELETE FROM person_to_group_table WHERE personId = :personId AND groupId = :groupId")
    suspend fun deleteWithIds(personId: Long, groupId: Long)

    @Query("DELETE FROM person_to_group_table WHERE groupId = :groupId")
    suspend fun deleteWithGroupId(groupId: Long)

    @Query("SELECT PERSON_TABLE.* " +
            "FROM person_to_group_table " +
            "INNER JOIN PERSON_TABLE ON PERSON_TABLE.id = PERSON_TO_GROUP_TABLE.personId " +
            "WHERE groupId = :groupId")
    fun getPeopleFromGroup(groupId: Long) : LiveData<List<Person>>

    @Query("SELECT PERSON_TABLE.* " +
            "FROM person_to_group_table " +
            "INNER JOIN PERSON_TABLE ON PERSON_TABLE.id = PERSON_TO_GROUP_TABLE.personId " +
            "WHERE groupId = :groupId")
    suspend fun getPeopleFromGroupSynchronous(groupId: Long) : List<Person>

    @Query("SELECT COUNT(*) FROM PERSON_TO_GROUP_TABLE WHERE groupId = :groupId")
    fun getGroupSize(groupId: Long): Int
}