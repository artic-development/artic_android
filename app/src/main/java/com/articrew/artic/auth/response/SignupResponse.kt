package com.articrew.artic.auth.response

data class SignupResponse(
    val birth: String,
    val id: String,
    val name: String,
    val pw: String
)