package com.articrew.artic.repository.remote

import com.articrew.artic.auth.Auth
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.remote.response.*
import com.articrew.artic.ui.new_archive.MakeNewArchiveData
import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDataSource(
    private val logger: Logger
) {
    private val retrofit: RetrofitInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Auth.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Observable 객체로 반환
            .build().create(RetrofitInterface::class.java)
    }

    fun getNewArticleList(): Observable<BaseResponse<List<ArticleResponse>>> {
        return retrofit.getNewArticleList()
    }

    fun getNewArchiveList(): Observable<BaseResponse<List<ArchiveResponse>>> {
        Auth.token?.let { token ->
            return retrofit.getNewArchiveList(contentType, token)
        }
        return createUninitializedToken()
    }

    fun getArticle(articleIdx: Int): Observable<BaseResponse<ArticleResponse>> {
        Auth.token?.let { token ->
            return retrofit.getArticle(contentType, token, articleIdx)
        }
        return createUninitializedToken()
    }

    // 아티클 좋아요 누르기
    fun postArticleLike(articleIdx: Int): Observable<BaseResponse<Int>> {
        Auth.token?.let { token ->
            return retrofit.postArticleLike(contentType, token, articleIdx)
        }
        return createUninitializedToken()
    }

    fun getArticPickList(): Observable<BaseResponse<List<ArticleResponse>>> {
        return retrofit.getArticPickList()
    }

    fun getCategoryList(): Observable<BaseResponse<List<CategoryResponse>>> {
        return retrofit.getCategoryList()
    }

    // @수민) 카테고리에 따른 아카이브 리스트
    fun getCategoryArchiveList(categoryIdx : Int): Observable<BaseResponse<List<ArchiveResponse>>> {
        Auth.token?.let {token ->
            return retrofit.getCategoryArchiveList(contentType, token, categoryIdx)
        }
        return createUninitializedToken()
    }

    fun getArchiveListGivenCategory(categoryIdx: Int): Observable<BaseResponse<List<ArchiveResponse>>> {
        Auth.token?.let { token ->
            return retrofit.getArchiveListGivenCategory(contentType, token, categoryIdx)
        }
        return createUninitializedToken()
    }

    fun getMyArchiveList(): Observable<BaseResponse<List<ArchiveResponse>>> {
        Auth.token?.let { token ->
            return retrofit.getMyArchiveList(contentType, token)
        }
        return createUninitializedToken()
    }

    fun getScrapArchiveList(): Observable<BaseResponse<List<ArchiveResponse>>> {
        Auth.token?.let { token ->
            return retrofit.getScrapArchiveList(contentType, token)
        }
        return createUninitializedToken()
    }

    fun getReadingHistoryArticle(): Observable<BaseResponse<List<ArticleResponse>>> {
        Auth.token?.let { token ->
            return retrofit.getReadingHistoryArticle(contentType, token)
        }
        return createUninitializedToken()
    }

    // @수민) 아티클 담기
    fun postCollectArticleInArchive(
        archiveIdx: Int,
        articleIdx: Int
    ): Observable<BaseResponse<Int>> {
        Auth.token?.let { token ->
            return retrofit.postCollectArticleInArchive(contentType, token, archiveIdx, articleIdx)
        }
        return createUninitializedToken()
    }

    fun postRegisterArchive(data: MakeNewArchiveData): Observable<BaseResponse<Int>> {
        Auth.token?.let { token ->
            return retrofit.postRegisterArchive(contentType, token, JsonObject().apply {
                addProperty("title", data.title)
                addProperty("img", data.img)
                addProperty("category_idx", data.categoryIdx)
            })
        }
        return createUninitializedToken()
    }

    fun postArticleRead(articleIdx: Int): Observable<BaseResponse<Int>> {
        Auth.token?.let { token ->
            return retrofit.postArticleRead(contentType,token,articleIdx)
        }
        return createUninitializedToken()

    }
    fun getArticleListGivenArchiveId(archiveId: Int): Observable<BaseResponse<List<ArticleResponse>>> {
        Auth.token?.let { token ->
            return retrofit.getArticleListGivenArchiveId(archiveId, contentType, token )
        }
        return createUninitializedToken()
    }

    // TODO RequestBody 같은 정보는 retrofit 에 강하게 종속인걸?
    fun putMyPageInfo(
        name: RequestBody,
        intro:RequestBody,
        img: MultipartBody.Part
    ): Observable<BaseResponse<Any>> {
        Auth.token?.let { token ->
            return retrofit.putMyPageInfo( token,name,intro,img)
        }
        return createUninitializedToken()
    }
    fun getMyPageInfo(): Observable<BaseResponse<MyPageResponse>> {
        Auth.token?.let {  token ->
            return retrofit.getMyPageInfo(contentType, token)
        }
        return createUninitializedToken()
    }

    fun getSearchArticleList(keyword: String): Observable<BaseResponse<List<ArticleResponse>>> {
        Auth.token?.let {  token ->
            return retrofit.getSearchArticleList(contentType, token, keyword)
        }
        return createUninitializedToken()
    }

    fun getSearchArchiveList(keyword: String): Observable<BaseResponse<List<ArchiveResponse>>> {
        Auth.token?.let {  token ->
            return retrofit.getSearchArchiveList(contentType, token, keyword)
        }
        return createUninitializedToken()
    }

    fun getSearchRecommendation(): Observable<BaseResponse<List<RecommendationResponse>>> {
        Auth.token?.let {  token ->
            return retrofit.getSearchRecommendation(contentType, token)
        }
        return createUninitializedToken()
    }

    fun postArchiveScrap(archiveIdx: Int): Observable<BaseResponse<Any>> {
        Auth.token?.let { token ->
            return retrofit.postArchiveScrap(contentType, token, archiveIdx)
        }
        return createUninitializedToken()
    }

    fun getNotification(): Observable<BaseResponse<List<NotificationResponse>>> {
        Auth.token?.let { token ->
            return retrofit.getNotification(contentType, token)
        }
        return createUninitializedToken()
    }

    fun readNotification(): Observable<BaseResponse<Any>> {
        Auth.token?.let {  token ->
            return retrofit.readNotification(contentType, token)
        }
        return createUninitializedToken()
    }

    fun getArchiveIsScarp(articleIdx: Int): Observable<BaseResponse<ArchiveScrapResponse>> {
        Auth.token?.let { token ->
            return retrofit.getArchiveIsScarp(contentType, token, articleIdx)
        }
        return createUninitializedToken()
    }

    private fun <T>createUninitializedToken(): Observable<BaseResponse<T>> {
        return Observable.just(BaseResponse.createUninitializedToken())
    }

    companion object {
        val contentType = "application/json"
    }
}