package com.android.artic.ui.setting.setting_version

import android.content.Context
import android.content.pm.PackageInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.android.artic.R

class SettingVersionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_version)
       // getVersionInfo()


    }

    //이런식으로 구현....?
//    fun getVersionInfo() {
//        val info: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
//        val version = info.versionName
//
//   }


}
