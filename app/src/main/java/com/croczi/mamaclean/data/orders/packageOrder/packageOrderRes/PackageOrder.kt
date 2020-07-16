package com.croczi.mamaclean.data.orders.packageOrder.packageOrderRes

import java.io.Serializable

data class PackageOrder(
    val createAt: String,
    val id: Int,
    val itemsCount: Int,
    val latitude: String,
    val longitude: String,
    val office: Office,
    val receivedAt: String,
    val status: String,
    val user: User,
    val user_Package: UserPackage
) : Serializable