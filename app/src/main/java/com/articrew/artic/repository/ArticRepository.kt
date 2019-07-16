package com.articrew.artic.repository

import com.articrew.artic.data.*
import com.articrew.artic.data.notification.*
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.local.LocalDataSource
import com.articrew.artic.repository.remote.RetrofitDataSource
import com.articrew.artic.repository.remote.mapper.*
import com.articrew.artic.repository.scheduler.ArticSchduler
import com.articrew.artic.ui.new_archive.MakeNewArchiveData
import com.articrew.artic.ui.search.data.RecommendWordData
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**
 * object that read, write data from server or local
 * @author greedy0110
 * */
class ArticRepository (
    private val logger: Logger,
    private val local: LocalDataSource,
    private val remote: RetrofitDataSource,
    private val scheduler: ArticSchduler
) {
    // @수민) 아카이브 스크랩 통신
    fun postArchiveScrap(archiveIdx: Int): Observable<String> {
        return remote.postArchiveScrap(archiveIdx)
            .subscribeOn(scheduler.io())
            .map { it.message }
            .observeOn(scheduler.ui())
    }

    // @수민) 아티클 담기 통신
    fun postCollectArticleInArchive(
        archiveIdx: Int,
        articleIdx: Int
    ): Observable<String> {
        return remote.postCollectArticleInArchive(archiveIdx, articleIdx)
            .subscribeOn(scheduler.io())
            .map { it.message }
            .observeOn(scheduler.ui())
    }

    // @수민) 좋아요 통신
    fun postArticleLike(articleIdx: Int): Observable<Boolean> {
        return remote.postArticleLike(articleIdx)
            .subscribeOn(scheduler.io())
            .map { it.success }
            .observeOn(scheduler.ui())
    }

    // @수민) 카테고리별 아카이브 리스트
    fun getCategoryArchiveList(categoryId: Int): Observable<List<Archive>> {
        return remote.getCategoryArchiveList(categoryId)
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data.map { ArchiveMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    /**
     * get CategoryList by Asynchronous
     * @author greedy0110
     * @see Category
     * @return Observable Category List
     * */
    fun getCategoryList(): Observable<List<Category>> {
        return remote.getCategoryList()
            .subscribeOn(scheduler.io()) // io thread 에서 네트워크 처리를 한다.
            .map { if (it.success) it.data!!.map { res -> CategoryMapper.to(res) } else listOf() }
            .observeOn(scheduler.ui())// ui 코드에서 사용할 반환값이므로 ui 갱신 가능하도록 ui thread 로 변경
    }

    /**
     * get an article by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticle(articleId: Int): Observable<Article> {
        return remote.getArticle(articleId)
            .subscribeOn(scheduler.io())
            .map { if(it.success && it.data != null) ArticleMapper.to(it.data) else Article.createDummy() }
            .observeOn(scheduler.ui())
    }

    /**
     * get article list which is selected by team artic by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticPickList(): Observable<List<Article>> {
        return remote.getArticPickList()
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data!!.map { ArticleMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    /**
     * get archive list given category id by async
     * @see Archive
     * @author greedy0110
     * */
    fun getArchiveListGivenCategory(categoryId: Int): Observable<List<Archive>> {
        return remote.getArchiveListGivenCategory(categoryId)
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data.map { ArchiveMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    /**
     * get new archive list by async
     * @see Archive
     * @author greedy0110
     * */
    fun getNewArchiveList(): Observable<List<Archive>> {
        return remote.getNewArchiveList()
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data!!.map { ArchiveMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    /**
     * get new article list by async
     * @see Article
     * @author greedy0110
     * */
    fun getNewArticleList(): Observable<List<Article>> {
        return remote.getNewArticleList()
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data.map { ArticleMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    /**
     * get article list given archive id by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticleListGivenArchive(archiveId: Int): Observable<List<Article>> {
        return remote.getArticleListGivenArchiveId(archiveId)
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data.map { ArticleMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    /**
     * 추천 검색어 (https://github.com/artic-development/artic_server/wiki/%EC%B6%94%EC%B2%9C-%EA%B2%80%EC%83%89%EC%96%B4)
     * @author greedy0110
     * */
    fun getRecommendWordList(): Observable<List<RecommendWordData>> {
        return remote.getSearchRecommendation()
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data.map { RecommendWordMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    fun getMyPageScrap(): Observable<List<Archive>>{
        return remote.getScrapArchiveList()
            .subscribeOn(scheduler.io())
            .map { if(it.success && it.data != null) it.data.map { ArchiveMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    // 나의 아카이브 조회
    fun getMyPageMe(): Observable<List<Archive>>{
        return remote.getMyArchiveList()
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data.map { ArchiveMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    fun getMyInfo(): Observable<MyPage>{
        return remote.getMyPageInfo()
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) MyPageMapper.to(it.data) else MyPage.createDummy() }
            .observeOn(scheduler.ui())
    }

    /**
     * get search article list given search keyword
     * @see Article
     * @author greedy0110
     * */
    fun getSearchArticleList(keyword: String): Observable<List<Article>> {
        return remote.getSearchArticleList(keyword)
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data.map { ArticleMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    /**
     * get search archive list given search keyword
     * @see Archive
     * @author greedy0110
     * */
    fun getSearchArchiveList(keyword: String): Observable<List<Archive>>{
        return remote.getSearchArchiveList(keyword)
            .subscribeOn(scheduler.io())
            .map { if(it.success && it.data != null) it.data.map { ArchiveMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    fun addMyArchive(data: MakeNewArchiveData): Observable<Int> {
        return remote.postRegisterArchive(data)
            .subscribeOn(scheduler.io())
            .map { it.status }
            .observeOn(scheduler.ui())
    }

    fun changeMyInfo(
        name: RequestBody,
        intro: RequestBody,
        img:MultipartBody.Part
    ): Observable<Int> {
        return remote.putMyPageInfo(name, intro, img)
            .subscribeOn(scheduler.io())
            .map { it.status }
            .observeOn(scheduler.ui())
    }

    fun readArticle(articleIdx: Int): Observable<Any> {
        return remote.postArticleRead(articleIdx)
            .subscribeOn(scheduler.io())
            .map { it.status as Any }
            .observeOn(scheduler.ui())
    }

    fun readingHistoryArticle(): Observable<List<Article>> {
        return remote.getReadingHistoryArticle()
            .subscribeOn(scheduler.io())
            .map { if(it.success && it.data != null) it.data.map { ArticleMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    /**
     * https://github.com/artic-development/artic_server/wiki/%EC%95%8C%EB%A6%BC%EC%A1%B0%ED%9A%8C
     * @author greedy0110
     * */
    fun getNotification(): Observable<List<AppNotification>> {
        return remote.getNotification()
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data.map { NotificationMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    fun readNotification(): Observable<Boolean> {
        return remote.readNotification()
            .subscribeOn(scheduler.io())
            .map { it.success }
            .observeOn(scheduler.ui())
    }

    fun getArchiveIsScrap(archiveId: Int): Observable<Boolean> {
        return remote.getArchiveIsScarp(archiveId)
            .subscribeOn(scheduler.io())
            .map { if (it.data == null) false else it.data.scrap }
            .observeOn(scheduler.ui())
    }
}


