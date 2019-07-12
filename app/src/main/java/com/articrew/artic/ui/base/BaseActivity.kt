package com.articrew.artic.ui.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.articrew.artic.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject

abstract class BaseActivity: AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()
    protected val logger: Logger by inject()

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

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        var view = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                // 23 버전 이상일 때 상태바 하얀 색상에 회색 아이콘 색상을 설정
                view.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.parseColor("f2f2f2")
            }
        }
        else if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            window.statusBarColor = Color.BLACK
        }
    }

}