package com.android.artic.repository.remote

import com.android.artic.data.Archive
import com.android.artic.repository.remote.response.ArchiveResponse
import com.android.artic.repository.remote.response.BaseResponse
import com.android.artic.repository.remote.response.ArticleResponse
import com.android.artic.repository.remote.response.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitInterface {
    @GET("/home/article/articles/new")
    fun getNewArticleList(): Call<BaseResponse<List<ArticleResponse>>>

    @GET("/home/article/{article_idx}")
    fun getArticle(
        @Path("article_idx") articleIdx: Int
    ): Call<BaseResponse<ArticleResponse>> // TODO 2019.07.07 현재 신규 아티클이 size 1개의 배열로 오고 있다. 수정후 적용 필요 - 승

    @GET("/home/archive/archives/new")
    fun getNewArchiveList(): Call<BaseResponse<List<ArchiveResponse>>>

    @GET("/home/article/pick")
    fun getArticPickList(): Call<BaseResponse<List<ArticleResponse>>>

    @GET("/category")
    fun getCategoryList(): Call<BaseResponse<List<CategoryResponse>>>

    @GET("/category/{category_idx}/archives")
    fun getCategoryArchiveList(
        @Path("category_idx") categoryIdx: Int
    ) : Call<BaseResponse<List<ArchiveResponse>>>

    // @수민) 아티클 좋아요
    @POST("/article/:article_idx/like")
    fun postArticleLike(
        @
    )
}