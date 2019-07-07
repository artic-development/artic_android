package com.android.artic.repository.remote

import com.android.artic.auth.Auth
import com.android.artic.repository.remote.response.ArchiveResponse
import com.android.artic.repository.remote.response.ArticleResponse
import com.android.artic.repository.remote.response.BaseResponse
import com.android.artic.repository.remote.response.CategoryResponse
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


}