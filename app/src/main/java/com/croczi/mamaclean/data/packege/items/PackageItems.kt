package com.croczi.mamaclean.data.packege.items

data class PackageItems(
    val createAt: String,
    val name_ar:String,
    val name_en:String,
    val description_ar: String,
    val description_en:String,
    val days:Int,
    val `file`: String,
    val id: Int,
    val name: String,
    val packageItems: List<PackageItem>,
    val price: Int,
    val currency:String,
    val visits_count: Int



)