package com.simcom.ecashier.model.room;

import androidx.room.Entity;
import androidx.room.Ignore;
@Entity
public class CollectionInfo {
    private int price;
    @Ignore
    private int totalPrice;
    @Ignore
    private int currentlyCollected;
    @Ignore
    private int moneyLeft;
    private int peopleCount;
    private int paymentsCount;

    private int collectionId;
    private int groupId;
    private String name;

    public CollectionInfo(int price, int peopleCount, int paymentsCount, int collectionId, int groupId, String name) {
        this.price = price;
        this.peopleCount = peopleCount;
        this.paymentsCount = paymentsCount;
        this.collectionId = collectionId;
        this.groupId = groupId;
        this.name = name;
        totalPrice = price*peopleCount;
        moneyLeft = price*(peopleCount-paymentsCount);
        currentlyCollected = price*paymentsCount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCurrentlyCollected(int currentlyCollected) {
        this.currentlyCollected = currentlyCollected;
    }

    public void setMoneyLeft(int moneyLeft) {
        this.moneyLeft = moneyLeft;
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
        return totalPrice;
    }

    public int getCurrentlyCollected() {
        return currentlyCollected;
    }

    public int getMoneyLeft() {
        return moneyLeft;
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
