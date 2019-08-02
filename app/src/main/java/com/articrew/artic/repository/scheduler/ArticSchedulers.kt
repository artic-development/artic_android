package com.articrew.artic.repository.scheduler

import io.reactivex.Scheduler

interface ArticSchedulers {
    fun io(): Scheduler
    fun ui(): Scheduler
}