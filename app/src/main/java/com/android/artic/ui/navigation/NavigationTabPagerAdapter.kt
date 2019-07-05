package com.android.artic.ui.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.artic.ui.BaseFragment
import com.android.artic.ui.category.CategoryFragment
import com.android.artic.ui.home.HomeFragment
import com.android.artic.ui.mypage.mypage.MyPageFragment
import com.android.artic.ui.notification.NotificationFragment

class NavigationTabPagerAdapter(
    private val fm: FragmentManager
) :FragmentStatePagerAdapter(fm) {
    private val fragments = listOf<BaseFragment>(
        HomeFragment(), CategoryFragment(), NotificationFragment(), MyPageFragment()
    )

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
}