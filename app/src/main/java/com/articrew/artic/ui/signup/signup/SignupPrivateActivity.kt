package com.articrew.artic.ui.signup.signup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.articrew.artic.R
import com.articrew.artic.auth.Auth
import com.articrew.artic.data.auth.Signin
import com.articrew.artic.data.auth.Signup
import com.articrew.artic.logger.Logger
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.login.login.LoginActivity
import com.articrew.artic.ui.navigation.NavigationActivity
import khronos.toDate
import kotlinx.android.synthetic.main.activity_signup_private.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.regex.Pattern

class SignupPrivateActivity : BaseActivity() {
    private val auth: com.articrew.artic.auth.Auth by inject { parametersOf(this) }

    val namePattern = Pattern.compile("^[가-힣]*.{0,15}\$", Pattern.CASE_INSENSITIVE) // 이름 형식
    val birthPattern = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$", Pattern.CASE_INSENSITIVE) // 날짜 형식

    private var loginActivated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_private)

        setListener()
    }

    private fun setListener() {
        // @수민) 완료 버튼 리스너
        signup_private_done_txt.setOnClickListener {
            if (signup_private_done_txt.currentTextColor == Color.parseColor("#4f80ff")) {
                signup()
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
                        loginActivated = true
                    }
                }
                else {
                    tv_act_signup_private_name_check_success.visibility = View.INVISIBLE
                    tv_act_signup_private_name_check_fail.visibility = View.VISIBLE
                    signup_private_done_txt.setTextColor(Color.parseColor("#cdcdcd"))
                    loginActivated = false
                }
            }
        })

        et_act_signup_private_birth.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val birthStr = et_act_signup_private_birth.text.toString()
                val birthMatcher = birthPattern.matcher(birthStr)

                // TODO (@수민) 생일 유효성 검사
                if (et_act_signup_private_birth.text.toString() != "" && birthMatcher.find()) {
                    tv_act_signup_private_birth_check_success.visibility = View.VISIBLE
                    tv_act_signup_private_birth_check_fail.visibility = View.INVISIBLE

                    if (tv_act_signup_private_name_check_success.visibility == View.VISIBLE) {
                        signup_private_done_txt.setTextColor(Color.parseColor("#4f80ff"))
                        loginActivated = true
                    }
                }
                else {
                    tv_act_signup_private_birth_check_success.visibility = View.INVISIBLE
                    tv_act_signup_private_birth_check_fail.visibility = View.VISIBLE
                    signup_private_done_txt.setTextColor(Color.parseColor("#cdcdcd"))
                    loginActivated = false
                }

            }
        })

        // @수민) 생년월일 keyboard 완료 버튼 리스너
        et_act_signup_private_birth?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (loginActivated) {
                        signup()
                    }
                    else {
                        toast("형식에 맞게 입력해주세요.")
                    }
                    return true
                }
                return false
            }
        })
    }

    private fun signup() {
        logger.log("ui signup")
        // @수민) 아이디와 비밀번호 모두 비어있지 않을 때 통신
        val id = intent.getStringExtra("id")
        val pw = intent.getStringExtra("pw")
        val name = et_act_signup_private_name.text.toString()
        val birth = et_act_signup_private_birth.text.toString()
        if (name.isNotEmpty() && birth.isNotEmpty()) {
            auth.requestSignup(data = Signup(id,pw, birth.toDate("yyyy-MM-dd"), name),
                successCallback = {
                    // 회원가입 성공한 것으로 자동 로그인하자!
                    auth.requestSignin(data = Signin(it.id, it.pw),
                        successCallback = {
                            // TODO 응답 받은 토큰을 저장할 것
                            logger.log("token data : $it")

                            val intent = Intent(this@SignupPrivateActivity, LoginActivity::class.java)

                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

                            this@SignupPrivateActivity.startActivity(intent)
//                            finish()
                        },
                        statusCallback = {
                                status, success, message ->
                            if (status == 400) {
                                toast(message)
                            }
                        }
                    )
                },
                statusCallback = { status, success, message ->
                    if (status == 400) {
                        toast(message)
                    }
                })
        }
        else {
            toast("이름과 생년월일을 입력해주세요.")
        }
    }
}
