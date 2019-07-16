package com.articrew.artic.repository.remote.response

data class BaseResponse<Data> (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data?
) {
    companion object {
        fun <Data>createUninitializedToken(): BaseResponse<Data> {
            return BaseResponse(0, false, "로그인 되지 않았습니다.", null)
        }
    }
}