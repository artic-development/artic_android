package com.articrew.artic.ui.reset_password

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.articrew.artic.R
import kotlinx.android.synthetic.main.activity_find_password.*

class FindPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_password)

        setListener()
    }

    private fun setListener() {
        // @수민) 이메일 텍스트필드
        et_act_find_pw_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btn_act_find_pw_get_auth_code.isActivated = et_act_find_pw_email.text.toString() != ""
            }
        })

        // @수민) 인증번호 받기 버튼 리스너
        btn_act_find_pw_get_auth_code.setOnClickListener {
            if(btn_act_find_pw_get_auth_code.isActivated) {
                // TODO 인증번호 받기 서버 통신 ...?
                val intent = Intent(this, InputAuthCodeActivity::class.java)

                startActivity(intent)
            }
        }


        // @수민) 로그인으로 돌아가기 리스너
        linear_act_find_pw_back_to_login.setOnClickListener {
            finish()
        }
    }
}
