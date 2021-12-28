package com.simcom.ecashier.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_to_collection_table")
class PersonToCollection(val collectionId: Long, val personId: Long, val hasPaid : Boolean, val time: Long?) {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}