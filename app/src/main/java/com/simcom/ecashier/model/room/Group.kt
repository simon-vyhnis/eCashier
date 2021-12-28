package com.simcom.ecashier.model.room

import androidx.room.*

@Entity(tableName = "group_table")
class Group(var name: String) {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0

}