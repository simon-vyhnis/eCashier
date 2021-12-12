package com.simcom.ecashier.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
class Person(val name: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}