package com.articrew.artic.repository.remote.response

data class MyPageResponse (
    val user_idx: Int,
    val userId: String?,
    val user_pw: String?,
    val userImg: String?,
    val user_type: String?,
    val userIntro: String?,
    val user_birth: String?,
    val userName: String,
    val token: String?,
    val salt: String?,
    val fieldCount: Int,
    val affectedRows: Int,
    val insertId: Int,
    val serverStatus: Int,
    val warningCount: Int,
    val message: String?,
    val protocol41: Boolean,
    val changedRows: Int
)