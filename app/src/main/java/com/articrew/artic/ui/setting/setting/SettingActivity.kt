package com.articrew.artic.ui.setting.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.articrew.artic.R
import com.articrew.artic.auth.Auth
import com.articrew.artic.ui.login.login_start.LoginStartActivity
import com.articrew.artic.ui.setting.setting_editprofile.SettingEditProfileActivity
import com.articrew.artic.ui.setting.setting_version.SettingVersionActivity
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SettingActivity : AppCompatActivity() {
    private val auth: com.articrew.artic.auth.Auth by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        setting_artic_account_logout.setOnClickListener {
            auth.logout {
                // TODO 첫 화면으로 이동하고, 기존의 Activity Stack을 전부 정리해준다. 스플래시 화면이 나오면 스플래쉬로 이동해야하나?
                startActivity(intentFor<LoginStartActivity>().newTask().clearTask())
            }
        }

        //프로필 수정 버튼 누르면 프로필 수정 페이지로
        setting_edit_profile_container.setOnClickListener{
            val intent= Intent(this, SettingEditProfileActivity::class.java)

            startActivity(intent)
        }

        //버전정보 누르면 버전 페이지로
        setting_version_info_container.setOnClickListener{
            val intent= Intent(this, SettingVersionActivity::class.java)
            startActivity(intent)
        }
    }
}
