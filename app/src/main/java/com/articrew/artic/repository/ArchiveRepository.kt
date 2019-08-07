package com.articrew.artic.repository

import com.articrew.artic.data.Archive
import com.articrew.artic.repository.local.LocalDataSource
import com.articrew.artic.repository.remote.RetrofitDataSource
import com.articrew.artic.repository.remote.mapper.ArchiveMapper
import com.articrew.artic.repository.scheduler.ArticSchedulers
import io.reactivex.Observable

class ArchiveRepository (
    private val local: LocalDataSource,
    private val remote: RetrofitDataSource,
    private val scheduler: ArticSchedulers
) {
    // @수민) 카테고리별 아카이브 리스트
    fun getCategoryArchiveList(categoryId: Int): Observable<List<Archive>> {
        return remote.getCategoryArchiveList(categoryId)
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data.map { ArchiveMapper.to(it) } else listOf() }
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
            .map { if (it.success && it.data != null) it.data.map { ArchiveMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    fun getMyPageScrap(): Observable<List<Archive>> {
        return remote.getScrapArchiveList()
            .subscribeOn(scheduler.io())
            .map { if(it.success && it.data != null) it.data.map { ArchiveMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    // 나의 아카이브 조회
    fun getMyPageMe(): Observable<List<Archive>> {
        return remote.getMyArchiveList()
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) it.data.map { ArchiveMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }

    /**
     * get search archive list given search keyword
     * @see Archive
     * @author greedy0110
     * */
    fun getSearchArchiveList(keyword: String): Observable<List<Archive>> {
        return remote.getSearchArchiveList(keyword)
            .subscribeOn(scheduler.io())
            .map { if(it.success && it.data != null) it.data.map { ArchiveMapper.to(it) } else listOf() }
            .observeOn(scheduler.ui())
    }
}