package com.android.artic.ui.signup.signup

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.android.artic.R
import kotlinx.android.synthetic.main.activity_signup_login.*

class SignupLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_login)

        setListener()
    }

    private fun setListener() {
        // @수민) 완료 버튼 리스너
        signup_login_next_txt.setOnClickListener {
            if (signup_login_next_txt.currentTextColor == Color.parseColor("#707070")) {
                var intent = Intent(this, SignupPrivateActivity::class.java)

                startActivity(intent)
            }
        }

        // @수민) TextChangedListener
        signup_login_edit_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (signup_login_edit_email.text.toString() != "" && signup_login_edit_password.text.toString() != "") {
                    signup_login_next_txt.setTextColor(Color.parseColor("#707070"))
                }
                else {
                    signup_login_next_txt.setTextColor(Color.parseColor("#cdcdcd"))
                }
            }
        })

        signup_login_edit_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (signup_login_edit_password.text.toString() != "" && signup_login_edit_email.text.toString() != "") {
                    signup_login_next_txt.setTextColor(Color.parseColor("#707070"))
                }
                else {
                    signup_login_next_txt.setTextColor(Color.parseColor("#cdcdcd"))
                }
            }
        })
    }
}
