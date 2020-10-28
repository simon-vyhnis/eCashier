package com.simcom.ecashier.model.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_to_group_table")
public class PersonToGroup {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int personId;

    private int groupId;

    public PersonToGroup(int personId, int groupId) {
        this.personId = personId;
        this.groupId = groupId;
    }

    public int getPersonId() {
        return personId;
    }
    public int getGroupId() {
        return groupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
