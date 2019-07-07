package com.android.artic.repository

import com.android.artic.data.Archive
import com.android.artic.data.Article
import com.android.artic.data.Category
import com.android.artic.data.notification.*
import com.android.artic.logger.Logger
import com.android.artic.repository.local.LocalDataSource
import com.android.artic.repository.remote.RemoteDataSource
import com.android.artic.repository.remote.response.BaseResponse
import com.android.artic.ui.search.data.RecommendWordData
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
        remote.getArticle(articleId).enqueue(
            createFromRemoteCallback(
                mapper = {
                    it.data!!.let { res ->
                        Article(
                            id = res.article_idx,
                            like = res.hits,
                            title_img_url = res.thumnail,
                            title = res.article_title,
                            url = res.link
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
                            like = res.hits,
                            title_img_url = res.thumnail,
                            title = res.article_title,
                            url = res.link
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
        remote.getNewArchiveList().enqueue(
            createFromRemoteCallback(
                mapper = {
                    if (it.data == null) listOf()
                    else it.data.map { res -> Archive(
                        id = res.archive_idx,
                        categories = res.category_all.map { cate -> cate.category_title },
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
                        like = res.hits,
                        title_img_url = res.thumnail,
                        title = res.article_title,
                        url = res.link)
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
    fun getArticleListGivenArchive(archiveId: Int): Call<List<Article>> {
        return Calls.response(local.getArticleListGivenArchive(archiveId))
    }

    // @수민
    fun getMyArchiveList() : Call<List<Archive>> {
        return Calls.response(local.getMyArchiveList())
    }

    fun getRecommendWordList() : Call<List<RecommendWordData>> {
        return Calls.response(local.getRecommendWordList())
    }



    fun getMyPageScrap():Call<List<Archive>>{
        return Calls.response(local.getMyPageScrap())
    }


    fun getMyPageMe() : Call<List<Archive>>{
        return Calls.response(local.getMyPageMe())
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
    fun getSearchArticleList(keyword: String): Call<List<Article>> {
        return Calls.response(local.getSearchArticleList(keyword))
    }

    /**
     * get search archive list given search keyword
     * @see Archive
     * @author greedy0110
     * */
    fun getSearchArchiveList(keyword: String): Call<List<Archive>> {
        return Calls.response(local.getSearchArchiveList(keyword))
    }

    data class SomeUIData(val s:String)
    data class SomeServerData(val integer: Int)

    fun getSomething(successCallback: (SomeUIData)->Unit, failCallback: (Throwable)->Unit) {
        Calls.response(
            SomeServerData(1)
        ).enqueue(object : Callback<SomeServerData>{
            override fun onFailure(call: Call<SomeServerData>, t: Throwable) {
                failCallback(t)
            }

            override fun onResponse(call: Call<SomeServerData>, response: Response<SomeServerData>) {
                response.body()?.let {
                    successCallback(SomeUIData(it.integer.toString()))
                }
            }
        })
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
                failCallback?.invoke(t)
            }

            override fun onResponse(
                call: Call<SERVER>,
                response: Response<SERVER>
            ) {
                response.body()?.let {
                    logger.log("from SERVER : \n$it")
                    statusCallback?.invoke(it.status, it.success, it.message)
                    successCallback(mapper(it))
                }
            }

        }
    }
}