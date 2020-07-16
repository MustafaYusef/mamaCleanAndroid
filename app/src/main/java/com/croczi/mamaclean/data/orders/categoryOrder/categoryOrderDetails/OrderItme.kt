package com.croczi.mamaclean.data.orders.categoryOrder.categoryOrderDetails

data class OrderItme(
    val count: Int,
    val id: Int,
    val item: Item,
    val type: String
)