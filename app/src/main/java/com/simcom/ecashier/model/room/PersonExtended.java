package com.simcom.ecashier.model.room;

import androidx.room.Embedded;

public class PersonExtended {
    @Embedded
    private Person person;
    private int collectionId;
    private int rank;

    public PersonExtended(Person person, int collectionId, int rank) {
        this.person = person;
        this.collectionId = collectionId;
        this.rank = rank;
    }

    public Person getPerson() {
        return person;
    }

    public boolean hasPaid() {
        return rank != 0;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public int getRank() {
        return rank;
    }
}
