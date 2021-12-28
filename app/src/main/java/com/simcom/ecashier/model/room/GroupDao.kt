package com.simcom.ecashier.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroupDao {
    @Insert
    suspend fun insert(group: Group)

    @Update
    suspend fun update(group: Group)

    @Delete
    suspend fun delete(group: Group)

    @Query("SELECT * FROM GROUP_TABLE ORDER BY name")
    fun allGroups(): LiveData<List<Group>>
}