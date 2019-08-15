package com.articrew.artic.ui.reset_password

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.articrew.artic.R
import kotlinx.android.synthetic.main.activity_input_auth_code.*

class InputAuthCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_auth_code)

        setListener()
    }

    private fun setListener() {
        // @수민) 인증번호 텍스트필드
        et_act_input_auth_code_auth_code.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btn_act_input_auth_code_complete.isActivated = et_act_input_auth_code_auth_code.text.toString() != ""
            }
        })

        btn_act_input_auth_code_complete.setOnClickListener {
            if (btn_act_input_auth_code_complete.isActivated) {
                // TODO 인증번호 맞는지 확인하는 코드 (서버 통신 ..?)
                var intent = Intent(this, ResetPasswordActivity::class.java)

                startActivity(intent)
            }
        }
    }
}
