package com.android.artic.repository

import com.android.artic.auth.Auth
import com.android.artic.data.*
import com.android.artic.data.notification.*
import com.android.artic.logger.Logger
import com.android.artic.repository.local.LocalDataSource
import com.android.artic.repository.remote.RemoteDataSource
import com.android.artic.repository.remote.response.BaseResponse
import com.android.artic.ui.new_archive.MakeNewArchiveData
import com.android.artic.ui.search.data.RecommendWordData
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.mock.Calls

/**
 * object that read, write data from server or local
 * @author greedy0110
 * */
class ArticRepository (
    private val logger: Logger,
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) {
    // @수민) 아카이브 스크랩 통신
    fun postArchiveScrap(
        archiveIdx: Int,
        successCallback: ((Int) -> Unit)? = null,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)
    ) {
        Auth.token?.let { token ->
            remote.postArchiveScrap(
                contentType = "application/json",
                token = token,
                archiveIdx = archiveIdx
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        it.status
                    },
                    successCallback = successCallback!!,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    // @수민) 아티클 담기 통신
    fun postCollectArticleInArchive(
        archiveIdx: Int,
        articleIdx: Int,
        successCallback: ((Int) -> Unit)? = null,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)
    ) {
        Auth.token?.let {token ->
            remote.postCollectArticleInArchive(
                contentType = "application/json",
                token = token,
                archiveIdx = archiveIdx,
                articleIdx = articleIdx
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        it.status
                    },
                    successCallback = successCallback!!,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    // @수민) 좋아요 통신
    fun postArticleLike(
        articleIdx: Int,
        successCallback: ((Int) -> Unit)? = null,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)
    ) {
        Auth.token?.let { token ->
            remote.postArticleLike(
                contentType = "application/json",
                token = token,
                articleIdx = articleIdx
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        it.status
                    },
                    successCallback = successCallback!!,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    // @수민) 카테고리별 아카이브 리스트
    fun getCategoryArchiveList(
        categoryId: Int,
        successCallback: (List<Archive>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
    ) {
        Auth.token?.let { token ->
            remote.getCategoryArchiveList(
                contentType = "application/json",
                token = token,
                categoryIdx = categoryId
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        if (it.data == null) listOf()
                        else it.data.map { res -> Archive(
                            id = res.archive_idx,
                            title = res.archive_title,
                            title_img_url = res.archive_img,
                            category_idx = res.category_idx,
                            num_article = res.article_cnt,
                            categories = res.category_all!!.map { cate -> cate.category_title },
                            scrap = res.scrap
                        ) }
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }


    }

    /**
     * get CategoryList by Asynchronous
     * @author greedy0110
     * @see Category
     * @return Callable Category List
     * */
    fun getCategoryList(
        successCallback: (List<Category>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
    ) {
        remote.getCategoryList().enqueue(
            createFromRemoteCallback(
                mapper = {
                    if (it.data == null) listOf()
                    else it.data.map { res->
                        Category(
                            id = res.category_idx,
                            name = res.category_title
                        )
                    }
                },
                successCallback = successCallback,
                failCallback = failCallback,
                statusCallback = statusCallback
            )
        )
    }

    fun getReadingHistoryArticleList():Call<List<Article>> {
        return Calls.response(local.getReadingHistoryArticleList())
    }

    /**
     * get an article by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticle(
        articleId: Int,
        successCallback: (Article) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
    ) {
        Auth.token?.let {token ->
            remote.getArticle(
                articleIdx = articleId,
                contentType = "application/json",
                token = token
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        it.data!!.let { res ->
                            Article(
                                id = res.article_idx,
                                like = res.like_cnt?:0,
                                title_img_url = res.thumnail,
                                title = res.article_title,
                                url = res.link,
                                domain_url = res.domain?:"",
                                isLiked = res.like
                            )
                        }
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    /**
     * get an article by async
     * extra -> archiveIdx: Int, archiveTitle: String
     * @see Article
     * @author greedy0110
     * */
    fun getArticleWithExtra(
        articleId: Int,
        successCallback: (Article, Pair<Int?, String?>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
    ) {
        Auth.token?.let { token ->
            remote.getArticle(
                articleIdx = articleId,
                contentType = "application/json",
                token = token
            ).enqueue(
                createFromRemoteCallbackAddExtra(
                    mapper = {
                        it.data!!.let { res ->
                            Article(
                                id = res.article_idx,
                                like = res.like_cnt ?: 0,
                                title_img_url = res.thumnail,
                                title = res.article_title,
                                url = res.link,
                                domain_url = res.domain?:""
                            )
                        }
                    },
                    extra = {
                        it.data!!.let { res ->
                            Pair(res.archive_idx, res.archive_title)
                        }
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    /**
     * get article list which is selected by team artic by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticPickList(
        successCallback: (List<Article>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
    ) {
        remote.getArticPickList().enqueue(
            createFromRemoteCallback(
                mapper = {
                    if (it.data == null) listOf()
                    else it.data.map { res ->
                        Article(
                            id = res.article_idx,
                            like = res.like_cnt?:0,
                            title_img_url = res.thumnail,
                            title = res.article_title,
                            url = res.link,
                            domain_url = res.domain?:""
                        )
                    }
                },
                successCallback = successCallback,
                failCallback = failCallback,
                statusCallback = statusCallback
            )
        )
    }

    /**
     * get archive name given archive Id by Asynchronous
     * @author greedy0110
     * */
    fun getArchiveName(archiveId: Int): Call<String> {
        return Calls.response(local.getArchiveName(archiveId))
    }

    /**
     * get dummy article list by asynchronous
     * @see Article
     * @author greedy0110
     * */
    fun getDummyArticleList(): Call<List<Article>> {
        return Calls.response(local.getDummyArticleList())
    }

    /**
     * get archive list given category id by async
     * @see Archive
     * @author greedy0110
     * */
    fun getArchiveListGivenCategory(
        categoryId: Int,
        successCallback: (List<Archive>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
        ){
        Auth.token?.let {token ->
            remote.getArchiveListGivenCategory(
                contentType = "application/json",
                token = token,
                categoryIdx = categoryId
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        logger.log("get archive list ${it.data}")
                        if (it.data == null) listOf()
                        else it.data.map { res -> Archive(
                            id = res.archive_idx,
                            category_ids = listOf(res.category_idx),
                            title = res.archive_title,
                            title_img_url = res.archive_img,
                            num_article = res.article_cnt,
                            scrap = res.scrap
                        ) }
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    /**
     * get new archive list by async
     * @see Archive
     * @author greedy0110
     * */
    fun getNewArchiveList(
        successCallback: (List<Archive>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null) {
        Auth.token?.let {token ->
            remote.getNewArchiveList(
                contentType = "application/json",
                token = token
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        if (it.data == null) listOf()
                        else it.data.map { res -> Archive(
                            id = res.archive_idx,
                            categories = res.category_all!!.map { cate -> cate.category_title },
                            category_ids = listOf(res.category_idx),
                            title = res.archive_title,
                            title_img_url = res.archive_img,
                            num_article = res.article_cnt,
                            scrap = res.scrap
                        ) }
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    /**
     * get new article list by async
     * @see Article
     * @author greedy0110
     * */
    fun getNewArticleList(
        successCallback: (List<Article>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null) {
        remote.getNewArticleList().enqueue(
            createFromRemoteCallback(
                mapper = {
                    if (it.data == null) listOf()
                    else it.data.map { res -> Article(
                        id = res.article_idx,
                        like = res.like_cnt?:0,
                        title_img_url = res.thumnail,
                        title = res.article_title,
                        url = res.link,
                        domain_url = res.domain?:""
                    )
                    }
                },
                successCallback = successCallback,
                failCallback = failCallback,
                statusCallback = statusCallback
            )
        )
    }



    /**
     * get article list given archive id by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticleListGivenArchive(
        archiveId: Int,
        successCallback: (List<Article>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
    ) {
        Auth.token?.let { token ->
            remote.getArticleListGivenArchiveId(
                archiveId = archiveId,
                contentType = "application/json",
                token = token
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        if (it.data == null) listOf()
                        else it.data.map { res ->
                            Article(
                                id = res.article_idx,
                                like = res.like_cnt?:0,
                                title_img_url = res.thumnail,
                                title = res.article_title,
                                url = res.link,
                                isLiked = res.like,
                                domain_url = res.domain?:""
                            )
                        }
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    // TODO (@수민) 내 아카이브 조회 아래 걸로 바꾼 후에 이거 지우긴
    fun getMyArchiveList() : Call<List<Archive>> {
        return Calls.response(local.getMyArchiveList())
    }


    fun getRecommendWordList() : Call<List<RecommendWordData>> {
        return Calls.response(local.getRecommendWordList())
    }



    fun getMyPageScrap(
        successCallback: (List<Archive>) -> Unit,
        failCallback: ((Throwable) -> Unit)?=null,
        statusCallback: ((Int, Boolean, String) -> Unit)?=null
    ){
        Auth.token?.let{token->
            remote.getScrapArchiveList(
                "application/json",token
            ).enqueue(
                createFromRemoteCallback(
                    mapper={
                        if(it.data==null)  listOf()
                        else it.data.map{res->Archive(
                            id = res.archive_idx,
                            category_ids = listOf(res.category_idx),
                            title = res.archive_title,
                            title_img_url = res.archive_img,
                            num_article = res.article_cnt,
                            category_title = res.category_title
                        )}
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback

                )
            )
        }
    }

    // 나의 아카이브 조회
    fun getMyPageMe(
        successCallback: (List<Archive>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
    ){
        Auth.token?.let {token ->
            remote.getMyArchiveList(
                "application/json", token
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        if (it.data == null) listOf()
                        else it.data.map { res -> Archive(
                            id = res.archive_idx,
                            category_ids = listOf(res.category_idx),
                            title = res.archive_title,
                            title_img_url = res.archive_img,
                            num_article = res.article_cnt
                        ) }
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    fun getMyInfo(
        successCallback: (MyPage) -> Unit,
        failCallback: ((Throwable) -> Unit)?=null,
        statusCallback: ((Int, Boolean, String) -> Unit)?=null
    ){
        Auth.token?.let{token->
            remote.getMyPageInfo(
                "application/json", token
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        if (it.data == null) throw IllegalArgumentException()
                        else it.data.let { res -> MyPage(
                            name = res.userName,
                            id = res.userId,
                            profile_img = res.userImg,
                            my_info = res.userIntro)
                        }
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )

        }
    }


    // TODO 어떻게 여러가지 타입의 서버에서 받아오는 데이터를 한번에 처리할 수 있을까?
    /**
     * get new notification list by async
     * @see AppNotification
     * @author greedy0110
     * */
    fun getNewNotificationList(): Call<List<AppNotification>> {
        return Calls.response(local.getNewNotificationList())
    }

    /**
     * get already read notification list by async
     * @see AppNotification
     * @author greedy0110
     * */
    fun getAlreadyReadNotificationList(): Call<List<AppNotification>> {
        return Calls.response(local.getAlreadyReadNotificationList())
    }

    /**
     * get search article list given search keyword
     * @see Archive
     * @author greedy0110
     * */
    fun getSearchArticleList(
        keyword: String,
        successCallback: (List<Article>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
    ) {
        Auth.token?.let {token ->
            remote.getSearchArticleList(
                contentType = "application/json",
                token = token,
                keyword = keyword
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        if (it.data == null) listOf()
                        else it.data.map { res ->
                            Article(
                                id = res.article_idx,
                                like = res.like_cnt?:0,
                                isLiked = res.like,
                                title_img_url = res.thumnail,
                                title = res.article_title,
                                url = res.link,
                                domain_url = res.domain?:""
                            )
                        }
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    /**
     * get search archive list given search keyword
     * @see Archive
     * @author greedy0110
     * */
    fun getSearchArchiveList(
        keyword: String,
        successCallback: (List<Archive>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
    ){
        Auth.token?.let {token ->
            remote.getSearchArchiveList(
                "application/json", token, keyword
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        if (it.data == null) listOf()
                        else it.data.map { res -> Archive(
                            id = res.archive_idx,
                            categories = res.category_all!!.map { ca -> ca.category_title },
                            category_ids = listOf(res.category_idx),
                            title = res.archive_title,
                            title_img_url = res.archive_img,
                            num_article = res.article_cnt
                        ) }
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    fun addMyArchive(
        data: MakeNewArchiveData,
        successCallback: (Int) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
    ) {
        Auth.token?.let {token ->
            remote.postRegisterArchive("application/json", token,
                JsonObject().apply {
                    addProperty("title", data.title)
                    addProperty("img", data.img)
                    addProperty("category_idx", data.categoryIdx)
                }
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                        it.status
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    fun changeMyInfo(
        name: RequestBody,
        intro: RequestBody,
        img:MultipartBody.Part,
        successCallback: (Int) -> Unit,
        failCallback: ((Throwable) -> Unit)?=null,
        statusCallback: ((Int, Boolean, String) -> Unit)?=null
    ){
        Auth.token?.let{token->
            remote.putMyPageInfo(token,
                name,
                intro,
                img
            ).enqueue(
                createFromRemoteCallback(
                    mapper={
                        it.status
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }

    fun readArticle(
        articleIdx: Int,
        successCallback: (Any) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null
    ) {
        Auth.token?.let { token ->
            remote.postArticleRead(
                contentType = "application/json",
                token = token,
                articleIdx = articleIdx
            ).enqueue(
                createFromRemoteCallback(
                    mapper = {
                    },
                    successCallback = successCallback,
                    failCallback = failCallback,
                    statusCallback = statusCallback
                )
            )
        }
    }
    fun readingHistoryArticle(
        successCallback: (List<Article>) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null) {

        Auth.token?.let{token->
        remote.getReadingHistoryArticle("application/json", token).enqueue(
            createFromRemoteCallback(
                mapper = {
                    if (it.data == null) listOf()
                    else it.data.map { res -> Article(
                        id = res.article_idx,
                        like = res.like_cnt?:0,
                        title_img_url = res.thumnail,
                        title = res.article_title,
                        url = res.link,
                        domain_url = res.domain?:"")
                    }
                },
                successCallback = successCallback,
                failCallback = failCallback,
                statusCallback = statusCallback
            )
        )
        }
    }



    /**
     * @param mapper transform server data to UI data
     * @param successCallback will be called when server interaction success
     * @param failCallback will be called when server interaction fail
     * @param statusCallback will be called when server interaction with no error
     * @author greedy0110
     * */
    private fun <UI, SERVER: BaseResponse<*>>createFromRemoteCallback(
        mapper: (SERVER) -> UI,
        successCallback: (UI) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null): Callback<SERVER>
    {
        logger.log("call createFromRemoteCallback")
        return object : Callback<SERVER> {
            override fun onFailure(call: Call<SERVER>, t: Throwable) {
                logger.error("createFromRemoteCallback error ${t.message}")
                failCallback?.invoke(t)
            }

            override fun onResponse(
                call: Call<SERVER>,
                response: Response<SERVER>
            ) {
                logger.log("on response")
                response.body()?.let {
                    logger.log("from SERVER : \n$it")
                    statusCallback?.invoke(it.status, it.success, it.message)
                    if (it.data != null)
                        successCallback(mapper(it))
                    else
                        logger.error("createFromRemoteCallback data is null")
                }
            }

        }
    }

    /**
     * @param mapper transform server data to UI data
     * @param successCallback will be called when server interaction success
     * @param failCallback will be called when server interaction fail
     * @param statusCallback will be called when server interaction with no error
     * @author greedy0110
     * */
    private fun <UI, SERVER: BaseResponse<*>, EXTRA>createFromRemoteCallbackAddExtra(
        mapper: (SERVER) -> UI,
        extra: (SERVER) -> EXTRA,
        successCallback: (UI, EXTRA) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((Int, Boolean, String) -> Unit)? = null): Callback<SERVER>
    {
        logger.log("call createFromRemoteCallback")
        return object : Callback<SERVER> {
            override fun onFailure(call: Call<SERVER>, t: Throwable) {
                logger.error("createFromRemoteCallback error ${t.message}")
                failCallback?.invoke(t)
            }

            override fun onResponse(
                call: Call<SERVER>,
                response: Response<SERVER>
            ) {
                response.body()?.let {
                    logger.log("from SERVER : \n$it")
                    statusCallback?.invoke(it.status, it.success, it.message)
                    if (it.data != null)
                        successCallback(mapper(it), extra(it))
                    else
                        logger.error("createFromRemoteCallbackAddExtra data is null")
                }
            }

        }
    }
}


