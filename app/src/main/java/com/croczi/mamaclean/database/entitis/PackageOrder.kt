package com.mustafayusef.sharay.database.entitis

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PackageOrder (
    @PrimaryKey(autoGenerate = false)
    var Item_id: Int?=null,
    var count: Int?=null,
    var newCount: Int?=null,

    var name: String?=null,
    var name_en: String?=null,
    var price: Int?=null,
    var image:String?=null
)