package com.android.artic.ui.mypage.mypage

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.artic.ui.base.BaseFragment

class MyPageTabLayoutAdapter(
    fm: FragmentManager,
    private val frags: List<BaseFragment>
): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): BaseFragment {
        return when (position) {
            in 0..frags.size -> frags[position]
            else -> throw IllegalArgumentException("my page tab layout adater 0..${frags.size}")
        }
    }

    override fun getCount(): Int = frags.size
}