package com.articrew.artic.repository

import com.articrew.artic.data.MyPage
import com.articrew.artic.repository.local.LocalDataSource
import com.articrew.artic.repository.remote.RetrofitDataSource
import com.articrew.artic.repository.remote.mapper.MyPageMapper
import com.articrew.artic.repository.scheduler.ArticSchedulers
import io.reactivex.Observable

class UserRepository (
    private val local: LocalDataSource,
    private val remote: RetrofitDataSource,
    private val scheduler: ArticSchedulers
) {
    fun getMyInfo(): Observable<MyPage> {
        return remote.getMyPageInfo()
            .subscribeOn(scheduler.io())
            .map { if (it.success && it.data != null) MyPageMapper.to(it.data) else MyPage.createDummy() }
            .observeOn(scheduler.ui())
    }

}