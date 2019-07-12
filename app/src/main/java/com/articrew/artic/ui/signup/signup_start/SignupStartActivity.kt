package com.articrew.artic.ui.signup.signup_start

import android.content.Intent
import android.os.Bundle
import com.articrew.artic.R
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.base.BaseSocialLoginActivity
import com.articrew.artic.ui.signup.signup.SignupLoginActivity
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.activity_signup_start.*

class SignupStartActivity : BaseSocialLoginActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_start)

        setOnClickListener()
    }

    private fun setOnClickListener() {
        // @수민) "이메일로 시작하기" 누르면 이메일로 회원가입하는 액티비티(SignupLoginActivity)로 이동
        relative_act_signup_start_email_login.setOnClickListener {
            var intent = Intent(this, SignupLoginActivity::class.java)

            startActivity(intent)
        }

        // @수민) "로그인하기" 누르면 로그인 시작 화면(LoginStartActivity)로 이동 -> 이 때, 이 액티비티에 앞선 액티비티가 로그인 시작 화면이므로 그냥 이 액티비티를 finish 해버린다.
        linear_act_signup_go_to_login_button.setOnClickListener {
            finish()
        }

        relative_act_signup_start_facebook_login.setOnClickListener {
            LoginManager.getInstance().logIn(this, facebookReadPermission)
        }
    }
}
