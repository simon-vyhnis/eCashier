package com.simcom.ecashier.model.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "collection_LOG_table")
public class CollectionLog {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int collectionId;
    private int personId;
    private long time;

    public CollectionLog(int collectionId, int personId, long time) {
        this.collectionId = collectionId;
        this.personId = personId;
        this.time = time;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public int getPersonId() {
        return personId;
    }

    public long getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
