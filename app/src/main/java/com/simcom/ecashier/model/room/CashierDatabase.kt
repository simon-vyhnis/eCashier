package com.simcom.ecashier.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Person::class, Group::class, PersonToGroup::class, Collection::class, CollectionLog::class],
    version = 5
)
abstract class CashierDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun groupDao(): GroupDao
    abstract fun personToGroupDao(): PersonToGroupDao
    abstract fun collectionDao(): CollectionDao
    abstract fun collectionLogDao(): CollectionLogDao
}