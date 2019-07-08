package com.android.artic.repository.remote

import com.android.artic.repository.remote.response.ArchiveResponse
import com.android.artic.repository.remote.response.ArticleResponse
import com.android.artic.repository.remote.response.BaseResponse
import com.android.artic.repository.remote.response.CategoryResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Path

interface RemoteDataSource {
    fun getNewArticleList(): Call<BaseResponse<List<ArticleResponse>>>
    fun getArticle(articleIdx: Int): Call<BaseResponse<ArticleResponse>>
    fun getNewArchiveList(): Call<BaseResponse<List<ArchiveResponse>>>
    fun getArticPickList(): Call<BaseResponse<List<ArticleResponse>>>
    fun getCategoryList(): Call<BaseResponse<List<CategoryResponse>>>
    fun getCategoryArchiveList(categoryIdx : Int) : Call<BaseResponse<List<ArchiveResponse>>>
    fun getArchiveListGivenCategory(categoryIdx: Int): Call<BaseResponse<List<ArchiveResponse>>>
    fun getReadingHistoryArticle(contentType: String, token: String): Call<BaseResponse<List<ArticleResponse>>>
    fun getMyArchiveList(contentType: String, token: String): Call<BaseResponse<List<ArchiveResponse>>>
    fun getScrapArchiveList(contentType: String, token:String): Call<BaseResponse<List<ArchiveResponse>>>
    fun postRegisterArchive(contentType: String, token: String, body: JsonObject): Call<BaseResponse<Int>>
    fun getArticleListGivenArchiveId(archiveId: Int, contentType: String, token: String): Call<BaseResponse<List<ArticleResponse>>>
    fun postArticleLike(contentType: String, token: String, articleIdx: Int) : Call<BaseResponse<Int>>
    fun postArticleRead(contentType: String, token : String, articleIdx: Int) : Call<BaseResponse<Int>>

    fun getSearchArticleList(
        contentType: String,
        token: String,
        keyword: String
    ): Call<BaseResponse<List<ArticleResponse>>>

    fun getSearchArchiveList(
        contentType: String,
        token: String,
        keyword: String
    ): Call<BaseResponse<List<ArchiveResponse>>>

    // @수민) 아티클 담기
    fun getCollectArticleInArchive(
        contentType: String,
        token: String,
        archiveId: Int,
        articleIdx: Int
    ): Call<BaseResponse<Int>>
}