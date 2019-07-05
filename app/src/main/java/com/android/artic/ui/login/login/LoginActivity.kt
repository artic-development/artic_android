package com.android.artic.ui.login.login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.android.artic.R
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textColorResource
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setListener()
    }

    private fun setListener() {
        // @수민) 완료 버튼 리스너
        btn_login_complete.setOnClickListener {
            if (et_login_email.text.toString() == "" && et_login_password.text.toString() == "") {
                toast("이메일과 비밀번호를 입력해주세요")
            }
            else if (et_login_email.text.toString() == "") {
                toast("이메일을 입력해주세요")
            }
            else if (et_login_password.text.toString() == "") {
                toast("비밀번호를 입력해주세요")
            }
            else {
                // TODO (@수민)완료 버튼 색상이 파란색으로 바꼈을 때(사용자가 이메일, 비밀번호 모두 쳤을 때) 로그인 통신
                var intent = Intent(this, HomeActivity::class.java)

                startActivity(intent)
            }
        }
    }
}
