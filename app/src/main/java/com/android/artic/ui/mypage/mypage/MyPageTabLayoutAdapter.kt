package com.android.artic.ui.mypage.mypage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyPageTabLayoutAdapter(
    fm: FragmentManager
): FragmentStatePagerAdapter(fm) {
    private val fs = listOf(
        MyPageScrapFragment(), MyPageMeFragment()
    )

    override fun getItem(position: Int): Fragment = fs[position]

    override fun getCount(): Int = fs.size
}