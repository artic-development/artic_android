package com.android.artic.repository.remote

import com.android.artic.data.Archive
import com.android.artic.repository.remote.response.ArchiveResponse
import com.android.artic.repository.remote.response.BaseResponse
import com.android.artic.repository.remote.response.ArticleResponse
import com.android.artic.repository.remote.response.CategoryResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.*

interface RetrofitInterface {

    @GET("/home/article/articles/new")
    fun getNewArticleList(): Call<BaseResponse<List<ArticleResponse>>>

    @GET("/home/article/{article_idx}/new")
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

    @GET("/home/archive/category/{category_idx}")
    fun getArchiveListGivenCategory(
        @Path("category_idx") categoryIdx: Int
    ): Call<BaseResponse<List<ArchiveResponse>>>

    @GET("/mypage/archive/mine")
    fun getMyArchiveList(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String
    ): Call<BaseResponse<List<ArchiveResponse>>>

    /**
     * https://github.com/artic-development/artic_server/wiki/%EC%95%84%EC%B9%B4%EC%9D%B4%EB%B8%8C-%EB%93%B1%EB%A1%9D
     * body
     *      title: String
     *      img: String? (if img == null, server will set default img)
     *      category_idx: Int? (if make my archive then it is set null)
     * @author greedy0110
     * */
    @POST("/archive")
    fun postRegisterArchive(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Body body: JsonObject
    ): Call<BaseResponse<Int>>

    @GET("/archive/{archive_idx}/articles")
    fun getArticleListGivenArchiveId(
        @Path("archive_idx") archiveId: Int,
        @Header("Content-Type") contentType: String,
        @Header("token") token: String
    ): Call<BaseResponse<List<ArticleResponse>>>

    @GET("/search/article")
    fun getSearchArticleList(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Query("keyword") keyword: String
    ): Call<BaseResponse<List<ArticleResponse>>>

    @GET("/search/archive")
    fun getSearchArchiveList(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Query("keyword") keyword: String
    ): Call<BaseResponse<List<ArchiveResponse>>>

    @POST("/archive/article/{article_idx}/history")
    fun postArticleRead(
        @Header("Content-Type") contentType: String,
        @Header("token") token:String,
        @Path("article_idx") articleIdx: Int
    ): Call<BaseResponse<Int>>

    @GET("mypage/archive/scrap")
    fun getScrapArchiveList(
        @Header("Content-Type") contentType: String,
        @Header("token") token:String
    ): Call<BaseResponse<List<ArchiveResponse>>>


}