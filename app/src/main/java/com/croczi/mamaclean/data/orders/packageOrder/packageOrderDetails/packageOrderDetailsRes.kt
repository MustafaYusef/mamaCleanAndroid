package com.croczi.mamaclean.data.orders.packageOrder.packageOrderDetails

data class packageOrderDetailsRes(
    val `data`: Data,
    val error: String,
    val message: String,
    val statusCode: Int
)