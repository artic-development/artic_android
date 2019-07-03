package com.android.artic.ui.login.login_start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.artic.R
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.login.login.LoginActivity
import kotlinx.android.synthetic.main.activity_login_start.*

class LoginStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_start)

        relative_email_login.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
        }
    }
}
