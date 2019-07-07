package com.android.artic.api.response

data class SigninResponse(
    val refreshToken: String,
    val token: String
)