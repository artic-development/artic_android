package com.articrew.artic.ui.signup.signup

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.articrew.artic.R
import com.articrew.artic.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_signup_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.regex.Pattern

class SignupLoginActivity : BaseActivity() {

    val emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
    val passwordPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$", Pattern.CASE_INSENSITIVE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_login)

        setListener()
    }

    private fun setListener() {
        // @수민) 완료 버튼 리스너
        signup_login_next_txt.setOnClickListener {
            goNext()
        }

        // @수민) TextChangedListener
        signup_login_edit_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 이메일 유효성 검사
                var emailStr = signup_login_edit_email.text.toString()
                var emailMatcher = emailPattern.matcher(emailStr)

                if (signup_login_edit_email.text.toString() != "" && emailMatcher.find()) {
                    tv_act_signup_login_email_check_success.visibility = View.VISIBLE
                    tv_act_signup_login_email_check_fail.visibility = View.INVISIBLE

                    if (tv_act_signup_login_password_check_success.visibility == View.VISIBLE) {
                        signup_login_next_txt.setTextColor(Color.parseColor("#707070"))
                    }
                }
                else {
                    tv_act_signup_login_email_check_success.visibility = View.INVISIBLE
                    tv_act_signup_login_email_check_fail.visibility = View.VISIBLE
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
                // 비밀번호 유효성 검사
                var passwordStr = signup_login_edit_password.text.toString()
                var passwordMathcer = passwordPattern.matcher(passwordStr)

                if (signup_login_edit_password.text.toString() != "" && passwordMathcer.find()) {
                    tv_act_signup_login_password_check_success.visibility = View.VISIBLE
                    tv_act_signup_login_password_check_fail.visibility = View.INVISIBLE

                    if (tv_act_signup_login_email_check_success.visibility == View.VISIBLE) {
                        signup_login_next_txt.setTextColor(Color.parseColor("#707070"))
                    }
                }
                else {
                    tv_act_signup_login_password_check_success.visibility = View.INVISIBLE
                    tv_act_signup_login_password_check_fail.visibility = View.VISIBLE
                    signup_login_next_txt.setTextColor(Color.parseColor("#cdcdcd"))
                }
            }
        })

        // @수민) 키보드 리스너 (비밀번호 입력 후)
        signup_login_edit_password?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    goNext()
                }
                return false
            }
        })
    }

    private fun goNext() {
        val id = signup_login_edit_email.text.trim().toString()
        val pw = signup_login_edit_password.text.toString()
        if (id.isNotEmpty() && pw.isNotEmpty()) {
            startActivity<SignupPrivateActivity>("id" to id, "pw" to pw)
        }
        else {
            toast("아이디와 비밀번호를 입력해주세요.")
        }
    }
}
