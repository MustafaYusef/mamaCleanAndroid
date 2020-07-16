package com.mustafayusef.sharay.database.entitis

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class catagoryOrder (
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var Item_id: Int?=null,
    var count: Int?=null,
    var type:String?=null,//wash or (dry and wash)

    var name: String?=null,
    var name_en: String?=null,
    var price: Int?=null,
    var currency: String?=null,
    var image:String?=null
)