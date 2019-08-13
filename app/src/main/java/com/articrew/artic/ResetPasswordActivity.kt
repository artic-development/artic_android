package com.articrew.artic

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.articrew.artic.ui.login.login_start.LoginStartActivity
import kotlinx.android.synthetic.main.activity_reset_password.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast
import java.util.regex.Pattern

class ResetPasswordActivity : AppCompatActivity() {

    val passwordPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$", Pattern.CASE_INSENSITIVE) // @수민) 비밀번호 패턴

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        setListener()
    }

    private fun setListener() {
        // @수민) 새 비밀번호 텍스트
        et_act_reset_pw_new_pw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btn_act_reset_pw_complete.isActivated = et_act_reset_pw_new_pw.text.toString() != ""
            }
        })

        // @수민) 완료 버튼
        btn_act_reset_pw_complete.setOnClickListener {
            if(btn_act_reset_pw_complete.isActivated) {
                // 비밀번호 유효성 검사
                var newPwStr = et_act_reset_pw_new_pw.text.toString()
                var passwordMathcer = passwordPattern.matcher(newPwStr)

                if (et_act_reset_pw_new_pw.text.toString() != "" && passwordMathcer.find()) {
                    // TODO 비밀번호 재설정 서버 통신
                    startActivity(intentFor<LoginStartActivity>().newTask().clearTask())
                }
                else {
                    toast("비밀번호를 형식에 맞게 재설정해주세요")
                }
            }
        }
    }
}
