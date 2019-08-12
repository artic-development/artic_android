package com.articrew.artic.repository

import com.articrew.artic.data.Category
import com.articrew.artic.repository.local.LocalDataSource
import com.articrew.artic.repository.remote.RetrofitDataSource
import com.articrew.artic.repository.remote.mapper.CategoryMapper
import com.articrew.artic.repository.scheduler.ArticSchedulers
import io.reactivex.Observable

class CategoryRepository (
    private val local: LocalDataSource,
    private val remote: RetrofitDataSource,
    private val scheduler: ArticSchedulers
) {
    /**
     * get CategoryList by Asynchronous
     * @author greedy0110
     * @see Category
     * @return Observable Category List
     * */
    fun getCategoryList(): Observable<List<Category>> {
        return remote.getCategoryList()
            .subscribeOn(scheduler.io()) // io thread 에서 네트워크 처리를 한다.
            .map { if (it.success && it.data != null) it.data.map { res -> CategoryMapper.to(res) } else listOf() }
            .observeOn(scheduler.ui())// ui 코드에서 사용할 반환값이므로 ui 갱신 가능하도록 ui thread 로 변경
    }
}