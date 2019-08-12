package com.articrew.artic.repository

import com.articrew.artic.data.notification.AppNotification
import com.articrew.artic.repository.local.LocalDataSource
import com.articrew.artic.repository.remote.RetrofitDataSource
import com.articrew.artic.repository.remote.mapper.NotificationMapper
import com.articrew.artic.repository.scheduler.ArticSchedulers
import io.reactivex.Observable

class NoticeRepository (
    private val local: LocalDataSource,
    private val remote: RetrofitDataSource,
    private val scheduler: ArticSchedulers
) {
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
}