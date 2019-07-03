package com.android.artic.ui.login.login

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.android.artic.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.textChangedListener

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_login_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_login_email.text.toString() != "") {
                    if (et_login_password.text.toString() != "") {
                        tv_login_complete_btn.setTextColor(Color.parseColor("#4f80ff"))
                    }
                    else {
                        tv_login_complete_btn.setTextColor(Color.parseColor("#cdcdcd"))
                    }
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
                if (et_login_password.text.toString() != "") {
                    if (et_login_email.text.toString() != "") {
                        tv_login_complete_btn.setTextColor(Color.parseColor("#4f80ff"))
                    }
                    else {
                        tv_login_complete_btn.setTextColor(Color.parseColor("#cdcdcd"))
                    }
                }
                else {
                    tv_login_complete_btn.setTextColor(Color.parseColor("#cdcdcd"))
                }
            }
        })
    }
}
