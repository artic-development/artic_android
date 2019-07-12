package com.articrew.artic.data.auth

import java.util.*

data class Signup (
    val id: String,
    val pw: String,
    val birth: Date,
    val name: String
)