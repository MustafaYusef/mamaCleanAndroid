package com.croczi.mamaclean.data.auth.profile.myPackage

import java.io.Serializable

data class Package(
    val createAt: String,
    val expireAt: String,
    val is_Active: Boolean,
    val packageDetails: PackageDetails,
    val same_Area: Boolean,
    val user_packageId: Int,
    val visits_count: Int
) : Serializable