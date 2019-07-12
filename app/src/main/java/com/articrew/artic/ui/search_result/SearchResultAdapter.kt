package com.articrew.artic.ui.search_result

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.articrew.artic.ui.base.BaseFragment

// TODO 얘가 search result 결과를 전부 받아서 그려주기만 해야한다. 즉, Search Result Activity 에서 전부 데이터 통신이 끝나고 데이터를 넘겨주면 다시 적용하도록 구성해야 한다?
class SearchResultAdapter (
    fm:FragmentManager,
    private val fragments: List<BaseFragment>
) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): BaseFragment {
        return when(position) {
            in 0..fragments.size -> fragments[position]
            else -> throw IllegalArgumentException()
        }

    }

    override fun getCount(): Int = fragments.size

}
