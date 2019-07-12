package com.articrew.artic.repository.local

import com.articrew.artic.data.Archive
import com.articrew.artic.data.Article
import com.articrew.artic.data.Category
import com.articrew.artic.data.MyPage
import com.articrew.artic.data.notification.AppNotification
import com.articrew.artic.ui.search.data.RecommendWordData
import retrofit2.Call

interface LocalDataSource {
    /**
     * get CategoryList by Asynchronous in device data source
     * @author greedy0110
     * @see Category
     * @return Callable Category List
     * */
    fun getCategoryList(): List<Category>

    /**
     * get reading history article list by Asynchronous in device data source
     * @author greedy0110
     * @see Category
     * @return Callable Category List
     * */
    fun getReadingHistoryArticleList(): List<Article>


    /**
     * get an article by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticle(articleId: Int): Article


    /**
     * get article list which is selected by team artic by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticPickList():List<Article>

    /**
     * get archive name given archive Id by Asynchronous
     * @author greedy0110
     * */
    fun getArchiveName(archiveId: Int): String

    /**
     * get dummy article list by asynchronous
     * @see Article
     * @author greedy0110
     * */
    fun getDummyArticleList(): List<Article>

    /**
     * get archive list given category id by async
     * @see Archive
     * @author greedy0110
     * */
    fun getArchiveListGivenCategory(categoryId: Int): List<Archive>

    /**
     * get new archive list by async
     * @see Archive
     * @author greedy0110
     * */
    fun getNewArchiveList(): List<Archive>

    /**
     * get new article list by async
     * @see Article
     * @author greedy0110
     * */
    fun getNewArticleList(): List<Article>

    /**
     * get article list given archive id by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticleListGivenArchive(archiveId: Int): List<Article>

    // @수민
    fun getMyArchiveList() : List<Archive>

    fun getMyPageInfo() : MyPage

    fun getScrapArchiveList() : List<Archive>

    fun getRecommendWordList() : List<RecommendWordData>

    fun getMyPageScrap():List<Archive>

    fun getMyPageMe() : List<Archive>

    fun getSearchArticleList(keyword: String): List<Article>

    /**
     * get search archive list given search keyword
     * @see Archive
     * @author greedy0110
     * */
    fun getSearchArchiveList(keyword: String): List<Archive>
}

