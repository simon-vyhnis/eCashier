package com.simcom.ecashier.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Person::class, Group::class, PersonToGroup::class, Collection::class, PersonToCollection::class],
    version = 6
)
abstract class CashierDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun groupDao(): GroupDao
    abstract fun personToGroupDao(): PersonToGroupDao
    abstract fun collectionDao(): CollectionDao
    abstract fun personToCollectionDao(): PersonToCollectionDao
}