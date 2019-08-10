package com.articrew.artic.repository

import com.articrew.artic.repository.local.LocalDataSource
import com.articrew.artic.repository.remote.RetrofitDataSource
import com.articrew.artic.repository.remote.mapper.RecommendWordMapper
import com.articrew.artic.repository.scheduler.ArticSchedulers
import com.articrew.artic.ui.search.data.RecommendWordData
import io.reactivex.Observable

class RecommendRepository (
    private val local: LocalDataSource,
    private val remote: RetrofitDataSource,
    private val scheduler: ArticSchedulers
) {
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
}