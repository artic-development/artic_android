package com.android.artic.ui.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.artic.R
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {
    private lateinit var pagerAdapter: NavigationTabPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        pagerAdapter = NavigationTabPagerAdapter(supportFragmentManager)

        tl_navigation.setupWithViewPager(vp_navigation)
        vp_navigation.adapter = pagerAdapter

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
