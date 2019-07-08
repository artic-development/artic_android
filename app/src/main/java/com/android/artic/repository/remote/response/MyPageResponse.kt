package com.android.artic.repository.remote.response

data class MyPageResponse (
    val user_idx: Int,
    val user_id: String,
    val user_pw: String,
    val user_img: String?,
    val user_type: String,
    val user_intro: String?,
    val user_birth: String,
    val user_name: String,
    val token: String?,
    val salt: String
)