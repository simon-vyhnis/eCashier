package com.simcom.ecashier.model.room

class CollectionInfo(
    var price: Int,
    var peopleCount: Int,
    var paymentsCount: Int,
    var collectionId: Long,
    var groupId: Long,
    var name: String
) {
    val totalPrice: Int
        get() = price * peopleCount
    val currentlyCollected: Int
        get() = price * paymentsCount
    val moneyLeft: Int
        get() = price * (peopleCount - paymentsCount)
}