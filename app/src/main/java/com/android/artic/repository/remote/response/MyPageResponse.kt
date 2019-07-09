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
    val salt: String,
    val fieldCount: Int,
    val affectedRows: Int,
    val insertId: Int,
    val serverStatus: Int,
    val warningCount: Int,
    val message: String,
    val protocol41: Boolean,
    val changedRows: Int
)