package com.croczi.mamaclean.data.orders.packageOrder.packageOrderRes

data class UserPackage(
    val expireAt: String,
    val id: Int,
    val packageId: Int,
    val visits_count: Int
)