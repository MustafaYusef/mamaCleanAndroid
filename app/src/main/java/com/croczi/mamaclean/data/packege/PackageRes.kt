package com.croczi.mamaclean.data.packege

data class PackageRes(
    val `data`: Data,
    val error: String,
    val message: String,
    val statusCode: Int
)