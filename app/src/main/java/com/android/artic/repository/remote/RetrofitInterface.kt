package com.android.artic.repository.remote

import com.android.artic.repository.remote.response.ArchiveResponse
import com.android.artic.repository.remote.response.ArticResponse
import com.android.artic.repository.remote.response.ArticleResponse
import com.android.artic.repository.remote.response.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {
    @GET("/home/article/articles/new")
    fun getNewArticleList(): Call<ArticResponse<List<ArticleResponse>>>

    @GET("/home/article/{article_idx}")
    fun getArticle(
        @Path("article_idx") articleIdx: Int
    ): Call<ArticResponse<ArticleResponse>> // TODO 2019.07.07 현재 신규 아티클이 size 1개의 배열로 오고 있다. 수정후 적용 필요 - 승

    @GET("/home/archive/archives/new")
    fun getNewArchiveList(): Call<ArticResponse<List<ArchiveResponse>>>

    @GET("/home/article/pick")
    fun getArticPickList(): Call<ArticResponse<List<ArticleResponse>>>

    @GET("/category")
    fun getCategoryList(): Call<ArticResponse<List<CategoryResponse>>>
}