package com.android.artic.ui.login.login_start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.artic.R
import com.android.artic.auth.Auth
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.login.login.LoginActivity
import com.android.artic.ui.navigation.NavigationActivity
import com.android.artic.ui.signup.signup_start.SignupStartActivity
import kotlinx.android.synthetic.main.activity_login_start.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class LoginStartActivity : BaseActivity() {
    private val auth: Auth by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_start)

        setButtonClickListener()

        // TODO 자동 로그인을 구현한다. 하지만 이 부분은 맨 처음 켜지는 화면, 즉 스플레시 화면에서 하는게 맞을것 - 2019.07.09 승민
        auth.autoLogin(
                successCallback = {
                    // 자동 로그인이 완료되면 Navigation 화면으로 이동하자!
                    // 전 activity 로그를 날려야한다.
                    startActivity(intentFor<NavigationActivity>().clearTask().newTask())
                },
        failCallback = {
            // TODO 실제 스플래시 화면에서는 자동 로그인 실패시 LoginStartActivity 로 넘어가야함 - 2019.07.09 승민
            //startActivity<LoginStartActivity>()
        }
        )
    }

    private fun setButtonClickListener() {
        // @수민) "이메일로 로그인" 누르면 로그인 액티비티로 이동
        relative_act_login_start_email_login.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
        }

        // @수민) "회원가입 하기" 누르면 회원가입 시작 액티비티(SignupStartActivity)로 이동
        linear_act_login_start_go_to_signup_button.setOnClickListener {
            var intent = Intent(this, SignupStartActivity::class.java)

            startActivity(intent)
        }
    }
}
