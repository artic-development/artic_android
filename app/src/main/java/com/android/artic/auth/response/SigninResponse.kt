package com.android.artic.auth.response

data class SigninResponse(
    val refreshToken: String,
    val token: String
)