package com.android.artic.ui.login.login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.android.artic.R
import com.android.artic.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textColorResource

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setListener()
    }

    private fun setListener() {
        // @수민) 완료 버튼 리스너
        tv_login_complete_btn.setOnClickListener {
            if (tv_login_complete_btn.currentTextColor == Color.parseColor("#4f80ff")) {
                // TODO (@수민)완료 버튼 색상이 파란색으로 바꼈을 때(사용자가 이메일, 비밀번호 모두 쳤을 때) 로그인 통신
                var intent = Intent(this, HomeActivity::class.java)

                startActivity(intent)
            }
        }

        // @수민) TextChangedListener
        et_login_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_login_password.text.toString() != "" && et_login_email.text.toString() != "") {
                    tv_login_complete_btn.setTextColor(Color.parseColor("#4f80ff"))
                }
                else {
                    tv_login_complete_btn.setTextColor(Color.parseColor("#cdcdcd"))
                }
            }
        })

        et_login_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_login_password.text.toString() != "" && et_login_email.text.toString() != "") {
                    tv_login_complete_btn.setTextColor(Color.parseColor("#4f80ff"))
                }
                else {
                    tv_login_complete_btn.setTextColor(Color.parseColor("#cdcdcd"))
                }
            }
        })
    }
}
