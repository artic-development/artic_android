package com.articrew.artic.ui.mypage.mypage

import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.articrew.artic.ui.HeightWrappingViewPager
import com.articrew.artic.ui.base.BaseFragment

class MyPagePagerAdapter(
    fm: FragmentManager,
    private val frags: List<BaseFragment>
): FragmentPagerAdapter(fm) {
    private var currentPosition = -1

    override fun getItem(position: Int): BaseFragment {
        return when (position) {
            in 0..frags.size -> frags[position]
            else -> throw IllegalArgumentException("my page tab layout adater 0..${frags.size}")
        }
    }

    override fun getCount(): Int = frags.size

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        if (position != currentPosition) {
            Log.d("articapp", "$currentPosition -> $position")
            val fragment = getItem(position)
            val pager = container as HeightWrappingViewPager?
            if (fragment.view != null && pager != null) {
                currentPosition = position
                fragment.view?.let{
                    pager.setPagingDisabled()
                    pager.measureCurrentView(it)
                }
            }
        }
    }
}