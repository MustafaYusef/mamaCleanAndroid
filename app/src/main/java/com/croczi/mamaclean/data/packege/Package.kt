package com.croczi.mamaclean.data.packege

data class Package(
    val createAt: String,
    val description_ar: String,
    val description_en: String,
    val `file`: String,
    val id: Int,
    val name_ar: String,
    val days:Int,
    val name_en: String,
    val price: Int,

    val currency:String,
    val visits_count: Int
)
