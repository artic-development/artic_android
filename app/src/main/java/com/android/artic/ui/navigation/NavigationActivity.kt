package com.android.artic.ui.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.android.artic.R
import com.android.artic.ui.BaseFragment
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {
    private lateinit var pagerAdapter: NavigationTabPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        pagerAdapter = NavigationTabPagerAdapter(supportFragmentManager)

        tl_navigation.setupWithViewPager(vp_navigation)
        vp_navigation.adapter = pagerAdapter
        vp_navigation.offscreenPageLimit = 4 // 모든 fragment 들을 메모리에 들고 있게끔 하였다. (mypage 에서 custom viewpager 부분에 에러가있다. 만약 fragment 를 다시 만들면 view pager 의 height 세팅이 제대로 동작하지 않음)

        val iconList = listOf(
            R.drawable.selector_tab_home,
            R.drawable.selector_tab_category,
            R.drawable.selector_tab_notice,
            R.drawable.selector_tab_profile
        )

        for (i in 0 until tl_navigation.tabCount) {
            tl_navigation.getTabAt(i)?.setIcon(iconList[i])
        }
    }
}