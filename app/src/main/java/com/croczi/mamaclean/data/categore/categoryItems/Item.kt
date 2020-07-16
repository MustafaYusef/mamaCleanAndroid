package com.croczi.mamaclean.data.categore.categoryItems

data class Item(
    val createAt: String,
    val currency: String,
    val dw_price: Int,
    val id: Int,
    val name_ar: String,
    val name_en: String,
    val photo: String,
    val wash_price: Int
)