package com.articrew.artic.ui.login.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.articrew.artic.R
import com.articrew.artic.auth.Auth
import com.articrew.artic.data.auth.Signin
import com.articrew.artic.logger.Logger
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import java.util.regex.Pattern

class LoginActivity : BaseActivity() {
    private val auth: com.articrew.artic.auth.Auth by inject()

    val emailPattern : Pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
    val passwordPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$", Pattern.CASE_INSENSITIVE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setListener()
    }

    private fun setListener() {
        // @수민) edittext 리스너를 설정하여 이메일과 비밀번호를 모두 쳤으면 로그인 버튼이 활성화되도록
        et_login_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btn_login_complete.isActivated = et_login_email.text.toString() != "" && et_login_password.text.toString() != ""
            }
        })

        et_login_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btn_login_complete.isActivated = et_login_email.text.toString() != "" && et_login_password.text.toString() != ""
            }
        })

        // @수민) 완료 버튼 리스너
        btn_login_complete.setOnClickListener {
            if(btn_login_complete.isActivated) {
                signin()
            }
        }

        // @수민) 비밀번호까지 입력한 후, 비밀번호 edittext에서 완료 누르면 작동
        et_login_password?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // @수민) 아이디와 비밀번호 모두 비어있지 않을 때 통신
                    if (et_login_email.text.toString() != "" && et_login_password.text.toString() != "") {
                        signin()

                        return true
                    }
                    else {
                        toast("아이디와 비밀번호를 입력해주세요.")
                    }
                }
                    return false
            }
        })
    }

    private fun signin() {
        // 유효성 검사
        // 이메일
        et_login_email.setText(et_login_email.text.trim())
        val emailStr = et_login_email.text.toString()
        val emailMatcher = emailPattern.matcher(emailStr)

        // 비밀번호
        val passwordStr = et_login_password.text.toString()
        val passwordMatcher = passwordPattern.matcher(passwordStr)
//        if (!emailMatcher.find() || !passwordMatcher.find()) {
//            toast("이메일과 비밀번호를 형식에 맞게 입력해주세요.")
//        }
//
//        else {
            logger.log("request signin")
            // TODO 로그인 구현이 되어야함!
            auth.requestSignin(
                data = Signin(emailStr, passwordStr),
                successCallback = {
                    // TODO 응답 받은 토큰을 저장할 것s
                    logger.log("token data : $it")

                    val intent = Intent(this@LoginActivity, NavigationActivity::class.java)

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

                    this@LoginActivity.startActivity(intent)
                },
                statusCallback = {
                        status, success, message ->
                    if (status == 400) {
                        toast(message)
                    }
                }
            )
//        }
    }
}
