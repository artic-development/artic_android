package com.articrew.artic.repository

import com.articrew.artic.data.Article
import com.articrew.artic.repository.local.LocalDataSource
import com.articrew.artic.repository.remote.RetrofitDataSource
import com.articrew.artic.repository.remote.mapper.ArticleMapper
import com.articrew.artic.repository.scheduler.ArticSchedulers
import io.reactivex.Observable

class ArticleRepository (
    private val local: LocalDataSource,
    private val remote: RetrofitDataSource,
    private val scheduler: ArticSchedulers
) {
    /**
     * get an article by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticle(articleId: Int): Observable<Article> {
        return remote.getArticle(articleId)
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) ArticleMapper.to(it.data) else Article.createDummy() }
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
            .map { if (it.success && it.data != null) it.data.map { ArticleMapper.to(it) } else listOf() }
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

    fun readingHistoryArticle(): Observable<List<Article>> {
        return remote.getReadingHistoryArticle()
            .subscribeOn(scheduler.io())
            .map { if(it.success && it.data != null) it.data.map { ArticleMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }
}