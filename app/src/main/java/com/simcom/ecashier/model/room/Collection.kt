package com.simcom.ecashier.model.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "collection_table")
public class Collection {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int groupId;
    private int price;
    private String name;
    private long time;
    private boolean current;

    public Collection(String name, int groupId, int price, long time, boolean current) {
        this.groupId = groupId;
        this.price = price;
        this.time = time;
        this.current = current;
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCurrent(boolean current){
        this.current = current;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getGroupId() {
        return groupId;
    }
    public int getPrice() {
        return price;
    }
    public long getTime() {
        return time;
    }
    public boolean isCurrent() {
        return current;
    }
}
