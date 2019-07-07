package com.android.artic.repository.remote

import com.android.artic.repository.remote.response.ArchiveResponse
import com.android.artic.repository.remote.response.ArticleResponse
import com.android.artic.repository.remote.response.ArticResponse
import com.android.artic.repository.remote.response.CategoryResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.Calls

class ArticApi : RemoteDataSource {
    companion object {
        private val BASE_URL = "http://15.164.11.203:3000"
    }

    private val retrofit: RetrofitInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitInterface::class.java)
    }

    override fun getNewArticleList(): Call<ArticResponse<List<ArticleResponse>>> {
        return retrofit.getNewArticleList()
    }

    override fun getNewArchiveList(): Call<ArticResponse<List<ArchiveResponse>>> {
        return retrofit.getNewArchiveList()
    }

    override fun getArticle(articleIdx: Int): Call<ArticResponse<ArticleResponse>> {
        return retrofit.getArticle(articleIdx)
    }

    override fun getArticPickList(): Call<ArticResponse<List<ArticleResponse>>> {
        return retrofit.getArticPickList()
    }

    override fun getCategoryList(): Call<ArticResponse<List<CategoryResponse>>> {
        return retrofit.getCategoryList()
    }
}