package com.android.artic.repository.remote.response

data class BaseResponse<Data> (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data?
)