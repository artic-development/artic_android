package com.android.artic.repository.remote

import com.android.artic.auth.Auth
import com.android.artic.repository.remote.response.ArchiveResponse
import com.android.artic.repository.remote.response.ArticleResponse
import com.android.artic.repository.remote.response.BaseResponse
import com.android.artic.repository.remote.response.CategoryResponse
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDataSource : RemoteDataSource {
    private val retrofit: RetrofitInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Auth.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitInterface::class.java)
    }

    override fun getNewArticleList(): Call<BaseResponse<List<ArticleResponse>>> {
        return retrofit.getNewArticleList()
    }

    override fun getNewArchiveList(): Call<BaseResponse<List<ArchiveResponse>>> {
        return retrofit.getNewArchiveList()
    }

    override fun getArticle(articleIdx: Int): Call<BaseResponse<ArticleResponse>> {
        return retrofit.getArticle(articleIdx)
    }

    override fun getArticPickList(): Call<BaseResponse<List<ArticleResponse>>> {
        return retrofit.getArticPickList()
    }

    override fun getCategoryList(): Call<BaseResponse<List<CategoryResponse>>> {
        return retrofit.getCategoryList()
    }

    // @수민) 카테고리에 따른 아카이브 리스트
    override fun getCategoryArchiveList(categoryIdx : Int): Call<BaseResponse<List<ArchiveResponse>>> {
        return retrofit.getCategoryArchiveList(categoryIdx)
    }
    override fun getArchiveListGivenCategory(categoryIdx: Int): Call<BaseResponse<List<ArchiveResponse>>> {
        return retrofit.getArchiveListGivenCategory(categoryIdx)
    }

    override fun getMyArchiveList(contentType: String, token: String): Call<BaseResponse<List<ArchiveResponse>>> {
        return retrofit.getMyArchiveList(contentType, token)
    }

    override fun postRegisterArchive(contentType: String, token: String, body: JsonObject): Call<BaseResponse<Int>> {
        return retrofit.postRegisterArchive(contentType, token, body)
    }

    override fun postArticleRead(
        contentType: String, token: String,articleIdx: Int
    ): Call<BaseResponse<Int>> {
        return retrofit.postArticleRead(contentType,token,articleIdx)

    }
    override fun getArticleListGivenArchiveId(archiveId: Int, contentType: String, token: String): Call<BaseResponse<List<ArticleResponse>>> {
        return retrofit.getArticleListGivenArchiveId(archiveId, contentType, token )
    }

    override fun getSearchArticleList(
        contentType: String,
        token: String,
        keyword: String
    ): Call<BaseResponse<List<ArticleResponse>>> {
        return retrofit.getSearchArticleList(contentType, token, keyword)
    }

    override fun getSearchArchiveList(
        contentType: String,
        token: String,
        keyword: String
    ): Call<BaseResponse<List<ArchiveResponse>>> {
        return retrofit.getSearchArchiveList(contentType, token, keyword)
    }
}