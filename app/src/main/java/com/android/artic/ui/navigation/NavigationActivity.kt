package com.android.artic.ui.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.android.artic.R
import com.android.artic.auth.Auth
import com.android.artic.data.auth.Signin
import com.android.artic.logger.Logger
import com.android.artic.ui.BaseFragment
import kotlinx.android.synthetic.main.activity_navigation.*
import org.koin.android.ext.android.inject

class NavigationActivity : AppCompatActivity() {
    private lateinit var pagerAdapter: NavigationTabPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        // TODO 테스트 용이다. 릴리즈 할때는 제거해야함!
        dummyAuth()

        pagerAdapter = NavigationTabPagerAdapter(supportFragmentManager)

        tl_navigation.setupWithViewPager(vp_navigation)
        tl_navigation.tabIconTint = null // 이거 없으면 안됌 선택시 tint 때문에 icon 이 덮힘
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

    private val auth: Auth by inject()
    private val logger: Logger by inject()
    private fun dummyAuth() {
        auth.requestSignin(
            Signin("soom9611@gmail.com", "wlgns2642"),
            successCallback = {
                logger.info("get dummy token ${it.token}")
            }
        )
    }
}