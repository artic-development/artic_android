package com.android.artic.ui.login.login_start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.artic.R
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.login.login.LoginActivity
import com.android.artic.ui.signup.signup_start.SignupStartActivity
import kotlinx.android.synthetic.main.activity_login_start.*

class LoginStartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_start)

        setButtonClickListener()
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
