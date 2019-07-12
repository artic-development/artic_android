package com.articrew.artic.ui.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.articrew.artic.ui.category.CategoryFragment
import com.articrew.artic.ui.home.HomeFragment
import com.articrew.artic.ui.mypage.mypage.MyPageFragment
import com.articrew.artic.ui.notification.NotificationFragment

class NavigationTabPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {
    // 필요할때 Fragment 를 만들어서 사용해야함. 맨 처음 화면이라 다른 프래그먼트 로드까지 하면 부하가 큼
    private var homeFragment: HomeFragment? = null

    private fun getHomeFragment():HomeFragment {
        if (homeFragment == null) homeFragment = HomeFragment()
        return homeFragment!!
    }

    private var categoryFragment: CategoryFragment? = null
    private fun getCategoryFragment():CategoryFragment {
        if (categoryFragment == null) categoryFragment = CategoryFragment()
        return categoryFragment!!
    }

    private var noticeFragment: NotificationFragment? = null
    private fun getNoticeFragment():NotificationFragment {
        if (noticeFragment == null) noticeFragment = NotificationFragment()
        return noticeFragment!!
    }

    private var myPageFragment: MyPageFragment? = null
    private fun getMyPageFragment():MyPageFragment {
        if (myPageFragment == null) myPageFragment = MyPageFragment()
        return myPageFragment!!
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> getHomeFragment()
            1 -> getCategoryFragment()
            2 -> getNoticeFragment()
            3 -> getMyPageFragment()
            else -> throw IllegalStateException("navigation 0-3")
        }
    }

    override fun getCount(): Int = 4
}