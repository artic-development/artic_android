package com.android.artic.ui.home

import android.os.Bundle
import com.android.artic.R
import com.android.artic.ui.BaseActivity

/**
 * 1. Home Fragment 들을 관리할 것
 * 2. Fragment 들의 위치는 유동적으로 변경가능 하며 HomeActivity 가 관리할 것
 * 3. Search Activity 를 시작할 수 있어야
 * @author greedy0110
 * */
class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}