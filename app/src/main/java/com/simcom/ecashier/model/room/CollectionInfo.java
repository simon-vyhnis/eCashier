package com.simcom.ecashier.model.room;

import androidx.room.Entity;
import androidx.room.Ignore;

public class CollectionInfo {



    public int price;
    public int peopleCount;
    public int paymentsCount;

    public int collectionId;
    public int groupId;
    public String name;

    public CollectionInfo(int price, int peopleCount, int paymentsCount, int collectionId, int groupId, String name) {
        this.price = price;
        this.peopleCount = peopleCount;
        this.paymentsCount = paymentsCount;
        this.collectionId = collectionId;
        this.groupId = groupId;
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public void setPaymentsCount(int paymentsCount) {
        this.paymentsCount = paymentsCount;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalPrice() {
        return price*peopleCount;
    }

    public int getCurrentlyCollected() {
        return price*(peopleCount-paymentsCount);
    }

    public int getMoneyLeft() {
        return price*paymentsCount;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public int getPaymentsCount() {
        return paymentsCount;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }
}
