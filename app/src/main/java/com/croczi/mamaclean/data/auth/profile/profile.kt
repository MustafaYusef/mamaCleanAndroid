package com.croczi.mamaclean.data.auth.profile

import java.io.Serializable

data class profile(
    val `data`: Data,
    val error: String,
    val message: String,
    val statusCode: Int
)