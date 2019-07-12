package com.articrew.artic.ui.base

import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

open class BaseOnPageChangeListener(
    val pagerAdatper: FragmentStatePagerAdapter
) : ViewPager.SimpleOnPageChangeListener() {
    private var currentPosition = 0

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        (pagerAdatper.getItem(currentPosition) as BaseFragment).onPauseFragment()
        (pagerAdatper.getItem(position) as BaseFragment).onResumeFragment()
        currentPosition = position
    }
}