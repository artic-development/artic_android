package com.android.artic.ui.search_result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.android.artic.R
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        search_result_viewpager.adapter=
            SearchResultAdapter(supportFragmentManager, 2)
        search_result_viewpager.offscreenPageLimit=2
        tl_search_result.setupWithViewPager(search_result_viewpager)

        val searchResultTabLayout: View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.search_result_tab,null,false)

        tl_search_result.getTabAt(0)!!.customView=searchResultTabLayout.findViewById(R.id.search_result_archive_tab) as RelativeLayout
        tl_search_result.getTabAt(1)!!.customView=searchResultTabLayout.findViewById(R.id.search_result_link_tab) as RelativeLayout

    }


}
