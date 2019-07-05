package com.android.artic.ui.login.login

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.android.artic.R
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.home.HomeActivity
import com.android.artic.ui.search_result.SearchResultActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup_login.*
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
                var intent = Intent(this, HomeActivity::class.java)

                startActivity(intent)
            }
        }

        // @수민) 비밀번호까지 입력한 후, 비밀번호 edittext에서 완료 누르면 작동
        et_login_password?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // @수민) 아이디와 비밀번호 모두 비어있지 않을 때 통신
                    if (et_login_email.text.toString() != "" && et_login_password.text.toString() != "") {
                        // TODO (@수민) 검색 기능 구현
                        var intent = Intent(this@LoginActivity, HomeActivity::class.java)

                        this@LoginActivity.startActivity(intent)

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
}
