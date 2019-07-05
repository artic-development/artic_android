package com.android.artic.ui.search_result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.android.artic.R
import com.android.artic.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_search_result.*

/**
 * it must need searchWord intent["searchKeyword"]
 */
class SearchResultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        // TODO 실제 검색 결과 데이터를 넘겨줘야 한다.
        search_result_viewpager.adapter = SearchResultAdapter(supportFragmentManager, 2, listOf(), listOf())
        search_result_viewpager.offscreenPageLimit=2
        tl_search_result.setupWithViewPager(search_result_viewpager)

        val searchResultTabLayout: View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.search_result_tab,null,false)



        tl_search_result.getTabAt(0)!!.customView = searchResultTabLayout.findViewById(R.id.search_result_archive_tab) as RelativeLayout
        tl_search_result.getTabAt(1)!!.customView = searchResultTabLayout.findViewById(R.id.search_result_link_tab) as RelativeLayout
    }
}