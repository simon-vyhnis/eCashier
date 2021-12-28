package com.simcom.ecashier.model.room

import androidx.room.*

@Entity(tableName = "collection_table")
class Collection(
    val name: String,
    val groupId: Long,
    val price: Int,
    val time: Long,
    var isCurrent: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}