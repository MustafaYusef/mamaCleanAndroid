package com.croczi.mamaclean.data.orders.categoryOrder.categoryOrderDetails

data class Order(
    val createAt: String,
    val id: Int,
    val itemsCount: Int,
    val latitude: String,
    val longitude: String,
    val orderItmes: List<OrderItme>,
    val price: Int,
    val receivedAt: String,
    val status: String
)