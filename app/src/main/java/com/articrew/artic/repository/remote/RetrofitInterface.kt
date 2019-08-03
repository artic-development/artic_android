package com.articrew.artic.repository.remote

import com.articrew.artic.repository.remote.response.*
import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.*

interface RetrofitInterface {

    /**
     * 신규 아티클 더보기 (https://github.com/artic-development/artic_server/wiki/%EC%8B%A0%EA%B7%9C-%EC%95%84%ED%8B%B0%ED%81%B4-%EB%8D%94%EB%B3%B4%EA%B8%B0)
     * @author greedy0110
     * */
    @GET("/home/article/articles/new")
    fun getNewArticleList(): Observable<BaseResponse<List<ArticleResponse>>>

    /**
     * 아티클 하나 선택시 (https://github.com/artic-development/artic_server/wiki/%EC%8B%A0%EA%B7%9C-%EC%95%84%ED%8B%B0%ED%81%B4-%EB%8D%94%EB%B3%B4%EA%B8%B0)
     * @author greedy0110
     * */
    @GET("/home/article/{article_idx}/new")
    fun getArticle(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Path("article_idx") articleIdx: Int
    ): Observable<BaseResponse<ArticleResponse>>

    /**
     * 신규 아카이브 더보기 (https://github.com/artic-development/artic_server/wiki/%EC%8B%A0%EA%B7%9C-%EC%95%84%EC%B9%B4%EC%9D%B4%EB%B8%8C-%EB%8D%94%EB%B3%B4%EA%B8%B0)
     * @author greedy0110
     * */
    // 신규 아카이브 받아오기
    @GET("/home/archive/archives/new")
    fun getNewArchiveList(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String
    ): Observable<BaseResponse<List<ArchiveResponse>>>

    /**
     * 아틱의 추천 (https://github.com/artic-development/artic_server/wiki/%EC%95%84%ED%8B%B1%EC%9D%98-%EC%B6%94%EC%B2%9C)
     * @author greedy0110
     * */
    @GET("/home/article/pick")
    fun getArticPickList(): Observable<BaseResponse<List<ArticleResponse>>>

    /**
     * 카테고리 (https://github.com/artic-development/artic_server/wiki/%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC)
     * @author greedy0110
     * */
    @GET("/category")
    fun getCategoryList(): Observable<BaseResponse<List<CategoryResponse>>>

    // @수민) 카테고리별 아카이브 리스트 GET
    /**
     * 아카이브 목록 조회 (https://github.com/artic-development/artic_server/wiki/%EC%95%84%EC%B9%B4%EC%9D%B4%EB%B8%8C-%EB%AA%A9%EB%A1%9D-%EC%A1%B0%ED%9A%8C)
     * @author 수민
     * */
    @GET("/category/{category_idx}/archives")
    fun getCategoryArchiveList(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Path("category_idx") categoryIdx: Int
    ): Observable<BaseResponse<List<ArchiveResponse>>>

    // @수민) 아티클 담기
    /**
     * 아티클 담기 (https://github.com/artic-development/artic_server/wiki/%EC%95%84%ED%8B%B0%ED%81%B4-%EB%8B%B4%EA%B8%B0)
     * @author 수민
     * */
    @POST("/archive/{archive_idx}/article/{article_idx}")
    fun postCollectArticleInArchive(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Path("archive_idx") archiveId: Int,
        @Path("article_idx") articleIdx: Int
    ): Observable<BaseResponse<Int>>

    // @수민) 아티클 좋아요
    /**
     * 아티클 좋아요 좋아요 취소 (https://github.com/artic-development/artic_server/wiki/%EC%95%84%ED%8B%B0%ED%81%B4-%EC%A2%8B%EC%95%84%EC%9A%94---%EC%A2%8B%EC%95%84%EC%9A%94-%EC%B7%A8%EC%86%8C)
     * @author 수민
     * */
    @POST("/archive/article/{article_idx}/like")
    fun postArticleLike(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Path("article_idx") articleIdx: Int
    ): Observable<BaseResponse<Int>> // response가 status, success, message만 있다면 data에 아무 값이나 넣어서 status 콜백 함수로 하면 됨


    // @수민) 아카이브 스크랩
    @POST("/archive/add/{archive_idx}")
    fun postArchiveScrap(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Path("archive_idx") archiveIdx: Int
    ): Observable<BaseResponse<Any>>

    /**
     * 홈 카테고리별 아카이브 (https://github.com/artic-development/artic_server/wiki/%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC-%EB%B3%84-%EC%95%84%EC%B9%B4%EC%9D%B4%EB%B8%8C-%EC%A1%B0%ED%9A%8C)
     * @author greedy0110
     * */
    @GET("/home/archive/category/{category_idx}")
    fun getArchiveListGivenCategory(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Path("category_idx") categoryIdx: Int
    ): Observable<BaseResponse<List<ArchiveResponse>>>

    // 내 아카이브 목록 GET
    /**
     * 내 아카이브 목록 (https://github.com/artic-development/artic_server/wiki/%EB%82%B4-%EC%95%84%EC%B9%B4%EC%9D%B4%EB%B8%8C-%EC%A1%B0%ED%9A%8C)
     * @author greedy0110
     * */
    @GET("/mypage/archive/mine")
    fun getMyArchiveList(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String
    ): Observable<BaseResponse<List<ArchiveResponse>>>

    /**
     * 아카이브 등록 (https://github.com/artic-development/artic_server/wiki/%EC%95%84%EC%B9%B4%EC%9D%B4%EB%B8%8C-%EB%93%B1%EB%A1%9D)
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
    ): Observable<BaseResponse<Int>>

    /**
     * 아티클 목록 (https://github.com/artic-development/artic_server/wiki/%EC%95%84%ED%8B%B0%ED%81%B4-%EB%AA%A9%EB%A1%9D)
     * @author
     * */
    @GET("/archive/{archive_idx}/articles")
    fun getArticleListGivenArchiveId(
        @Path("archive_idx") archiveId: Int,
        @Header("Content-Type") contentType: String,
        @Header("token") token: String
    ): Observable<BaseResponse<List<ArticleResponse>>>

    /**
     * 아티클 검색 (https://github.com/artic-development/artic_server/wiki/%EC%95%84%ED%8B%B0%ED%81%B4-%EA%B2%80%EC%83%89)
     * @author greedy0110
     * */
    @GET("/search/article")
    fun getSearchArticleList(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Query("keyword") keyword: String
    ): Observable<BaseResponse<List<ArticleResponse>>>

    /**
     * 아카이브 검색 (https://github.com/artic-development/artic_server/wiki/%EC%95%84%EC%B9%B4%EC%9D%B4%EB%B8%8C-%EA%B2%80%EC%83%89)
     * @author greedy0110
     * */
    @GET("/search/archive")
    fun getSearchArchiveList(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Query("keyword") keyword: String
    ): Observable<BaseResponse<List<ArchiveResponse>>>

    /**
     * 아티클 읽기 (아티클 클릭) (https://github.com/artic-development/artic_server/wiki/%EC%95%84%ED%8B%B0%ED%81%B4-%EC%9D%BD%EA%B8%B0-(%EC%95%84%ED%8B%B0%ED%81%B4-%ED%81%B4%EB%A6%AD))
     * @author 경희
     * */
    @POST("/archive/article/{article_idx}/history")
    fun postArticleRead(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String,
        @Path("article_idx") articleIdx: Int
    ): Observable<BaseResponse<Int>>

    //@경희)마이페이지 스크랩한 아카이브 get
    /**
     * 스크랩 한 아카이브 목록 (https://github.com/artic-development/artic_server/wiki/%EC%8A%A4%ED%81%AC%EB%9E%A9%ED%95%9C-%EC%95%84%EC%B9%B4%EC%9D%B4%EB%B8%8C-%EB%AA%A9%EB%A1%9D)
     * @author 경희
     * */
    @GET("/mypage/archive/scrap")
    fun getScrapArchiveList(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String
    ): Observable<BaseResponse<List<ArchiveResponse>>>

    /**
     * 최근 읽은 아티클 보기 (https://github.com/artic-development/artic_server/wiki/%EC%B5%9C%EA%B7%BC-%EC%9D%BD%EC%9D%80-%EC%95%84%ED%8B%B0%ED%81%B4-%EB%8D%94%EB%B3%B4%EA%B8%B0)
     * @author 경희
     * */
    @GET("/home/article/history")
    fun getReadingHistoryArticle(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String
    ): Observable<BaseResponse<List<ArticleResponse>>>

    /**
     * 마이페이지 조회 (https://github.com/artic-development/artic_server/wiki/%EB%A7%88%EC%9D%B4%ED%8E%98%EC%9D%B4%EC%A7%80-%EC%A1%B0%ED%9A%8C)
     * @author 경희
     * */
    @GET("/mypage")
    fun getMyPageInfo(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String
    ): Observable<BaseResponse<MyPageResponse>>

    /**
     * 마이페이지 수정 (https://github.com/artic-development/artic_server/wiki/%EB%A7%88%EC%9D%B4%ED%8E%98%EC%9D%B4%EC%A7%80-%EC%88%98%EC%A0%95)
     * @author 경희
     * */
    @Multipart
    @PUT("/mypage")
    fun putMyPageInfo(
        @Header("token") token: String,

        @Part("name") name: RequestBody,
        @Part("intro") intro: RequestBody,
        @Part img: MultipartBody.Part
    ): Observable<BaseResponse<Any>>

    /**
     * 추천 검색어 (https://github.com/artic-development/artic_server/wiki/%EC%B6%94%EC%B2%9C-%EA%B2%80%EC%83%89%EC%96%B4)
     * @author greedy0110
     * */
    @GET("/search/recommendation")
    fun getSearchRecommendation(
        @Header("Content-Type") contentType: String,
        @Header("token") token: String
    ): Observable<BaseResponse<List<RecommendationResponse>>>

    /**
     * 알림조희 (https://github.com/artic-development/artic_server/wiki/%EC%95%8C%EB%A6%BC%EC%A1%B0%ED%9A%8C)
     * @author greedy0110
     * */
    @GET("/notification")
    fun getNotification(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String
    ): Observable<BaseResponse<List<NotificationResponse>>>

    /**
     * 읽음으로 바꿈 (https://github.com/artic-development/artic_server/wiki/%EC%9D%BD%EC%9D%8C%EC%9C%BC%EB%A1%9C-%EB%B0%94%EA%BF%88)
     * @author greedy0110
     * */
    @PUT("/notification/read")
    fun readNotification(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String
    ): Observable<BaseResponse<Any>>

    @GET("/archive/{archive_idx}/scrap")
    fun getArchiveIsScarp(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("token") token: String,
        @Path("archive_idx") articleIdx: Int
    ): Observable<BaseResponse<ArchiveScrapResponse>>
}