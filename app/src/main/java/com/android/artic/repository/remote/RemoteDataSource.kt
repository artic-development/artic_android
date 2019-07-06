package com.android.artic.repository.remote

import com.android.artic.repository.remote.response.ArticleResponse
import com.android.artic.repository.remote.response.ArticResponse
import retrofit2.Call

interface RemoteDataSource {
    fun getNewArticleList(): Call<ArticResponse<List<ArticleResponse>>> // 실제 서버에서 받아올 데이터
}