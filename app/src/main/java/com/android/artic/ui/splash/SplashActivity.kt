package com.android.artic.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.artic.R
import com.android.artic.auth.Auth
import com.android.artic.logger.Logger
import com.android.artic.ui.base.BaseSocialLoginActivity
import com.android.artic.ui.login.login_start.LoginStartActivity
import com.android.artic.ui.navigation.NavigationActivity
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class SplashActivity : BaseSocialLoginActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        auth.autoLogin(
            activity = this,
            successCallback = {
                // 자동 로그인이 완료되면 Navigation 화면으로 이동하자!
                // 전 activity 로그를 날려야한다.
                startActivity(intentFor<NavigationActivity>().clearTask().newTask())
            },
            failCallback = {
                startActivity<LoginStartActivity>()
            }
        )
    }
}
