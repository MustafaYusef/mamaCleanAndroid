package com.croczi.mamaclean.data.auth.profile.myPackegeItems

data class item(
    val categoryId: Int,
    val createAt: String,
    val discont: Int,
    val dw_price: Int,
    val id: Int,
    val min_discont: Int,
    val name_ar: String,
    val name_en:String,
    val photo: String,
    val softDelete: Boolean,
    val wash_price: Int
)