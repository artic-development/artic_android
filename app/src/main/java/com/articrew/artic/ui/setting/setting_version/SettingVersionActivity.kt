package com.articrew.artic.ui.setting.setting_version

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.articrew.artic.R
import kotlinx.android.synthetic.main.activity_setting_version.*
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast

class SettingVersionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_version)
       // getVersionInfo()

        setUpdateBtn(true)
    }

    // @수민) 업데이트 해야할 때 버튼 리스너 연결
    private fun setUpdateBtn(updated : Boolean) {
        // TODO (@수민) 버전 가져와서 업데이트가 안되어있으면 업데이트 버튼으로 바꾸고, 업데이트가 된 상태면 최신 버전이라는 걸 알려줘야 함
        if (updated) { // updated가 true면 이미 업데이트된 상태이므로 최신 버전 버튼
            tv_act_setting_version_version_info.text = "현재 최신 버전입니다."
            tv_act_setting_version_version_info.textColor = Color.parseColor("#000000")

            tv_act_setting_version_version_info.background = ContextCompat.getDrawable(this, R.drawable.version_card_round)
        }
        else { // updated가 false면 업데이트 되지 않은 상태이므로 업데이트 버튼으로 바꿔야 함
            tv_act_setting_version_version_info.text = "새 버전 업데이트하기"
            tv_act_setting_version_version_info.textColor = Color.parseColor("#ffffff")

            tv_act_setting_version_version_info.background = ContextCompat.getDrawable(this, R.drawable.version_card_round_update)

            tv_act_setting_version_version_info.setOnClickListener {
                toast("업데이트 해야 함")
            }
        }
    }

    //이런식으로 구현....?
//    fun getVersionInfo() {
//        val info: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
//        val version = info.versionName
//
//   }


}
