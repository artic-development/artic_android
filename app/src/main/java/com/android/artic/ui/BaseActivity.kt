package com.android.artic.ui

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.artic.R
import com.android.artic.ui.category.CategoryActivity
import com.android.artic.ui.home.HomeActivity
import com.android.artic.ui.mypage.mypage.MyPageActivity
import com.android.artic.ui.notification.NotificationActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.bottom_navigation.*

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

    override fun onResume() {
        super.onResume()

        // 상속받은 activity의 setContentView 호출 후에 호출되어야 하므로 onResume에 추가
        // bottom navigation 세팅!
        global_bottom_navigation?.apply {
            // 이거 해줘야 우라가 menu에서 선택한 icon이 selector 동작을 정확히 수행함.
            itemIconTintList = null

            if (status != -1)
                selectedItemId = status

            setOnNavigationItemSelectedListener {
                if (status == it.itemId) false
                else {
                    status = it.itemId
                    when (it.itemId) {
                        R.id.navigation_home -> {
                            startActivity(Intent(this@BaseActivity, HomeActivity::class.java))
                        }
                        R.id.navigation_category -> {
                            startActivity(Intent(this@BaseActivity, CategoryActivity::class.java))
                        }
                        R.id.navigation_notice -> {
                            startActivity(Intent(this@BaseActivity, NotificationActivity::class.java))
                        }
                        R.id.navigation_profile -> {
                            startActivity(Intent(this@BaseActivity, MyPageActivity::class.java))
                        }
                        else -> false
                    }
                }
                true
            }
        }
    }

    companion object {
        var status: Int = -1
    }
}