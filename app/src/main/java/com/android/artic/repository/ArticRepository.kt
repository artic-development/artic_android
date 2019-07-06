package com.android.artic.repository

import com.android.artic.data.Archive
import com.android.artic.data.Article
import com.android.artic.data.Category
import com.android.artic.data.notification.*
import com.android.artic.repository.local.LocalDataSource
import com.android.artic.repository.remote.RemoteDataSource
import com.android.artic.repository.remote.response.ArticResponse
import com.android.artic.repository.remote.response.ArticleResponse
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
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) {
    /**
     * get CategoryList by Asynchronous
     * @author greedy0110
     * @see Category
     * @return Callable Category List
     * */
    fun getCategoryList(): Call<List<Category>> {
        return Calls.response(local.getCategoryList())
    }

    fun getReadingHistoryArticleList():Call<List<Article>> {
        return Calls.response(local.getReadingHistoryArticleList())
    }

    /**
     * get an article by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticle(articleId: Int): Call<Article> {
        return Calls.response(local.getArticle(articleId))
    }

    /**
     * get article list which is selected by team artic by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticPickList():Call<List<Article>> {
        return Calls.response(local.getArticPickList())
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
    fun getArchiveListGivenCategory(categoryId: Int): Call<List<Archive>>{
        return Calls.response(local.getArchiveListGivenCategory(categoryId))
    }

    /**
     * get new archive list by async
     * @see Archive
     * @author greedy0110
     * */
    fun getNewArchiveList(): Call<List<Archive>> {
        return Calls.response(local.getNewArchiveList())
    }

    /**
     * get new article list by async
     * @see Article
     * @author greedy0110
     * */
    fun getNewArticleList(successCallback: (List<Article>) -> Unit, failCallback: (Throwable) -> Unit, statusCallback: ((Int, Boolean, String) -> Unit)? = null) {
        remote.getNewArticleList().enqueue(
            object : Callback<ArticResponse<List<ArticleResponse>>> {
                override fun onFailure(call: Call<ArticResponse<List<ArticleResponse>>>, t: Throwable) {
                     failCallback(t)
                }

                override fun onResponse(
                    call: Call<ArticResponse<List<ArticleResponse>>>,
                    response: Response<ArticResponse<List<ArticleResponse>>>
                ) {
                    response.body()?.let {
                        statusCallback?.invoke(it.status, it.success, it.message)

                        successCallback(
                            it.data.map { res -> Article(
                                id = res.article_idx,
                                like = res.hits,
                                title_img_url = res.thumnail,
                                title = res.article_title,
                                url = res.link
                                ) }
                        )
                    }
                }

            }
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
}


