package com.android.artic.repository.remote

import com.android.artic.repository.remote.response.ArticleResponse
import com.android.artic.repository.remote.response.ArticResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.Calls

class ArticApi : RemoteDataSource {
    companion object {
        private val BASE_URL = "http://15.164.11.203:8080"
    }

    private val retrofit: RetrofitInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitInterface::class.java)
    }

    override fun getNewArticleList(): Call<ArticResponse<List<ArticleResponse>>> {
        return Calls.response(
            ArticResponse(
            200, true, "홈 신규 아티클/아카이브 조회 성공",
            listOf(
                ArticleResponse(
                    article_idx = 48,
                    article_title = "독일의 폭염에 대처하는 자세",
                    thumnail = "https://img.theqoo.net/img/xRxVm.jpg",
                    link = "https://brunch.co.kr/@mariandbook/581",
                    hits = 0,
                    date = "2019-07-04T04:18:10.000Z"
                ),ArticleResponse(
                    article_idx = 48,
                    article_title = "독일의 폭염에 대처하는 자세",
                    thumnail = "https://img.theqoo.net/img/xRxVm.jpg",
                    link = "https://brunch.co.kr/@mariandbook/581",
                    hits = 0,
                    date = "2019-07-04T04:18:10.000Z"
                ),ArticleResponse(
                    article_idx = 48,
                    article_title = "독일의 폭염에 대처하는 자세",
                    thumnail = "https://img.theqoo.net/img/xRxVm.jpg",
                    link = "https://brunch.co.kr/@mariandbook/581",
                    hits = 0,
                    date = "2019-07-04T04:18:10.000Z"
                ),ArticleResponse(
                    article_idx = 48,
                    article_title = "독일의 폭염에 대처하는 자세",
                    thumnail = "https://img.theqoo.net/img/xRxVm.jpg",
                    link = "https://brunch.co.kr/@mariandbook/581",
                    hits = 0,
                    date = "2019-07-04T04:18:10.000Z"
                ),ArticleResponse(
                    article_idx = 48,
                    article_title = "독일의 폭염에 대처하는 자세",
                    thumnail = "https://img.theqoo.net/img/xRxVm.jpg",
                    link = "https://brunch.co.kr/@mariandbook/581",
                    hits = 0,
                    date = "2019-07-04T04:18:10.000Z"
                ),ArticleResponse(
                    article_idx = 48,
                    article_title = "독일의 폭염에 대처하는 자세",
                    thumnail = "https://img.theqoo.net/img/xRxVm.jpg",
                    link = "https://brunch.co.kr/@mariandbook/581",
                    hits = 0,
                    date = "2019-07-04T04:18:10.000Z"
                )
            )
            )
        )
    }
}