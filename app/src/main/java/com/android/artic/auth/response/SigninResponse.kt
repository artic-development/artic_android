package com.android.artic.auth.response

data class SigninResponse(
    val refreshToken: String? = null,
    val token: String
)