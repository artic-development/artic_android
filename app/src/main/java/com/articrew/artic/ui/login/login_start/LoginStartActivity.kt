package com.articrew.artic.ui.login.login_start

import android.content.Intent
import android.os.Bundle
import com.articrew.artic.R
import com.articrew.artic.ui.base.BaseSocialLoginActivity
import com.articrew.artic.ui.login.login.LoginActivity
import com.articrew.artic.ui.signup.signup_start.SignupStartActivity
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.activity_login_start.*

class LoginStartActivity : BaseSocialLoginActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_start)

        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        // @수민) "이메일로 로그인" 누르면 로그인 액티비티로 이동
        btn_login_start_email_login.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
        }

        // @수민) "회원가입 하기" 누르면 회원가입 시작 액티비티(SignupStartActivity)로 이동
        linear_act_login_start_go_to_signup_button.setOnClickListener {
            var intent = Intent(this, SignupStartActivity::class.java)

            startActivity(intent)
        }

        btn_login_start_facebook_login.setOnClickListener {
            LoginManager.getInstance().logIn(this, facebookReadPermission)
        }
    }
}
