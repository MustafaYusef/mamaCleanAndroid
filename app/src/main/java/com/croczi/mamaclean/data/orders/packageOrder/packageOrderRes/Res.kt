package com.croczi.mamaclean.data.orders.packageOrder.packageOrderRes

data class Res(
    val `data`: Data,
    val error: String,
    val message: String,
    val statusCode: Int
)