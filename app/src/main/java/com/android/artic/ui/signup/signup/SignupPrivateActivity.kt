package com.android.artic.ui.signup.signup

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.android.artic.R
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_signup_login.*
import kotlinx.android.synthetic.main.activity_signup_private.*

class SignupPrivateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_private)

        setListener()
    }

    private fun setListener() {
        // @수민) 완료 버튼 리스너
        signup_private_done_txt.setOnClickListener {
            if (signup_private_done_txt.currentTextColor == Color.parseColor("#4f80ff")) {
                // TODO (@수민) 회원가입 완료 통신 구현
                var intent = Intent(this, HomeActivity::class.java)

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

                startActivity(intent)
//                finish()
            }
        }

        // @수민) TextChangedListener
        et_act_signup_private_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_act_signup_private_name.text.toString() != "" && et_act_signup_private_birth.text.toString() != "") {
                    signup_private_done_txt.setTextColor(Color.parseColor("#4f80ff"))
                }
                else {
                    signup_private_done_txt.setTextColor(Color.parseColor("#cdcdcd"))
                }
            }
        })

        et_act_signup_private_birth.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_act_signup_private_birth.text.toString() != "" && et_act_signup_private_name.text.toString() != "") {
                    signup_private_done_txt.setTextColor(Color.parseColor("#4f80ff"))
                }
                else {
                    signup_private_done_txt.setTextColor(Color.parseColor("#cdcdcd"))
                }
            }
        })
    }
}
