package com.articrew.artic.repository.scheduler

import io.reactivex.Scheduler

interface ArticSchduler {
    fun io(): Scheduler
    fun ui(): Scheduler
}