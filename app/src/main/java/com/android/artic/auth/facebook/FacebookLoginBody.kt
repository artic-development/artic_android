package com.android.artic.auth.facebook

data class FacebookLoginBody (
    val id: String,
    val email: String?,
    val name: String,
    val img: String?
)