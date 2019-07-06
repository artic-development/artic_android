package com.android.artic.ui.mypage.mypage

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.artic.ui.HeightWrappingViewPager

class MyPageTabLayoutAdapter(
    fm: FragmentManager
): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MyPageScrapFragment()
            1 -> MyPageMeFragment()
            else -> throw IllegalArgumentException("my page tab layout adater 0..1")
        }
    }

    override fun getCount(): Int = 2
}