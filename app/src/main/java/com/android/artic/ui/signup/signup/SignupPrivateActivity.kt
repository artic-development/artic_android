package com.android.artic.ui.signup.signup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.android.artic.R
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_signup_private.*
import org.jetbrains.anko.toast
import java.util.regex.Pattern

class SignupPrivateActivity : BaseActivity() {

    val namePattern = Pattern.compile("^[가-힣]*.{0,15}\$", Pattern.CASE_INSENSITIVE) // 이름 형식
    val birthPattern = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$", Pattern.CASE_INSENSITIVE) // 날짜 형식

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
                var intent = Intent(this, NavigationActivity::class.java)

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

                startActivity(intent)
            }
        }

        // @수민) TextChangedListener
        et_act_signup_private_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var nameStr = et_act_signup_private_name.text.toString()
                var nameMatcher = namePattern.matcher(nameStr)

                if (et_act_signup_private_name.text.toString() != "" && nameMatcher.find()) {
                    tv_act_signup_private_name_check_success.visibility = View.VISIBLE
                    tv_act_signup_private_name_check_fail.visibility = View.INVISIBLE

                    if (tv_act_signup_private_birth_check_success.visibility == View.VISIBLE) {
                        signup_private_done_txt.setTextColor(Color.parseColor("#4f80ff"))
                    }
                }
                else {
                    tv_act_signup_private_name_check_success.visibility = View.INVISIBLE
                    tv_act_signup_private_name_check_fail.visibility = View.VISIBLE
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
                var birthStr = et_act_signup_private_birth.text.toString()
                var birthMatcher = birthPattern.matcher(birthStr)

                // TODO (@수민) 생일 유효성 검사
                if (et_act_signup_private_birth.text.toString() != "" && birthMatcher.find()) {
                    tv_act_signup_private_birth_check_success.visibility = View.VISIBLE
                    tv_act_signup_private_birth_check_fail.visibility = View.INVISIBLE

                    if (tv_act_signup_private_name_check_success.visibility == View.VISIBLE) {
                        signup_private_done_txt.setTextColor(Color.parseColor("#4f80ff"))
                    }
                }
                else {
                    et_act_signup_private_birth.visibility = View.INVISIBLE
                    et_act_signup_private_birth.visibility = View.VISIBLE
                    signup_private_done_txt.setTextColor(Color.parseColor("#cdcdcd"))
                }
            }
        })

        // @수민) 생년월일 keyboard 완료 버튼 리스너
        et_act_signup_private_birth?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // @수민) 아이디와 비밀번호 모두 비어있지 않을 때 통신
                    if (et_act_signup_private_name.text.toString() != "" && et_act_signup_private_birth.text.toString() != "") {
                        var intent = Intent(this@SignupPrivateActivity, NavigationActivity::class.java)

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

                        this@SignupPrivateActivity.startActivity(intent)

                        return true
                    }
                    else {
                        toast("이름과 생년월일을 입력해주세요.")
                    }
                }
                return false
            }
        })
    }
}
