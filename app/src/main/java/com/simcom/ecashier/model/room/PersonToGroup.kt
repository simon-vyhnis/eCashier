package com.simcom.ecashier.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_to_group_table")
class PersonToGroup(val personId: Long, val groupId: Long) {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0

}