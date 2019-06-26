package com.android.artic.ui

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity: AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    /**
     * registry Disposable for clear when activity is gone
    * @author greedy0110
     * @see Disposable
    * */
    protected fun addDisposable(dis: Disposable) {
        compositeDisposable.add(dis)
    }

    // 종료할 때 해당 화면에서 정리해야하는 Disposable 을 전부 정리해준다.
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}