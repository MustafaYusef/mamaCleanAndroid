package com.croczi.mamaclean.data.orders.categoryOrder.categoryOrdersRes

data class categoryOrderRes(
    val `data`: Data,
    val error: String,
    val message: String,
    val statusCode: Int
)