package com.android.artic.ui

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity: AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(dis: Disposable) {
        compositeDisposable.add(dis)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}