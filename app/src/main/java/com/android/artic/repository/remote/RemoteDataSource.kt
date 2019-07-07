package com.android.artic.repository.remote

import com.android.artic.repository.remote.response.ArchiveResponse
import com.android.artic.repository.remote.response.ArticleResponse
import com.android.artic.repository.remote.response.ArticResponse
import com.android.artic.repository.remote.response.CategoryResponse
import retrofit2.Call

interface RemoteDataSource {
    fun getNewArticleList(): Call<ArticResponse<List<ArticleResponse>>>
    fun getArticle(articleIdx: Int): Call<ArticResponse<ArticleResponse>>
    fun getNewArchiveList(): Call<ArticResponse<List<ArchiveResponse>>>
    fun getArticPickList(): Call<ArticResponse<List<ArticleResponse>>>
    fun getCategoryList(): Call<ArticResponse<List<CategoryResponse>>>
}