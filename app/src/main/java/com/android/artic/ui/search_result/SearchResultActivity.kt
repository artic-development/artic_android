package com.android.artic.ui.search_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.android.artic.R
import com.android.artic.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.search_result_tab.*

/**
 * it must need searchWord intent["searchKeyword"]
 */
class SearchResultActivity : BaseActivity() {
    private var searchKeyword = ""
    private lateinit var adapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        searchKeyword = intent.getStringExtra("searchKeyword")

        adapter = SearchResultAdapter(supportFragmentManager, 2, searchKeyword).apply {
            searchCount.subscribe {
                search_result_number.text = it.toString()
            }
        }
        search_result_viewpager.adapter = adapter
        search_result_viewpager.offscreenPageLimit=2

        search_result_viewpager.addOnPageChangeListener(
            object : ViewPager.OnPageChangeListener {
                private var currentPosition = 0

                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                // Page 가 변경될때 마다. 상단에 검색 결과가 변경, 반영되어야 한다.
                override fun onPageSelected(position: Int) {
                    adapter.getItem(currentPosition)?.onPauseFragment()
                    adapter.getItem(position)?.onResumeFragment()
                    currentPosition = position

                    when (position) {
                        0 -> {
                            selectArchiveTab()
                        }
                        1 -> {
                            selectArticleTab()
                        }
                    }
                }
            }
        )
        tl_search_result.setupWithViewPager(search_result_viewpager)

        val searchResultTabLayout: View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.search_result_tab,null,false)


        tl_search_result.getTabAt(0)!!.customView = searchResultTabLayout.findViewById(R.id.search_result_archive_tab) as RelativeLayout
        tl_search_result.getTabAt(1)!!.customView = searchResultTabLayout.findViewById(R.id.search_result_link_tab) as RelativeLayout

        selectArchiveTab() // 초기에는 archive tab이 선택되어 있다.
    }

    // TODO 유틸로 뺄 수 있니?
    private fun initAllTabItem() {
        tv_search_result_tab_archive.setTextColor(ContextCompat.getColor(this, R.color.brown_grey))
        img_search_result_tab_archive.visibility = View.INVISIBLE

        tv_search_result_tab_article.setTextColor(ContextCompat.getColor(this, R.color.brown_grey))
        img_search_result_tab_article.visibility = View.INVISIBLE
    }

    private fun selectArchiveTab() {
        initAllTabItem()
        tv_search_result_tab_archive.setTextColor(ContextCompat.getColor(this, R.color.soft_blue))
        img_search_result_tab_archive.visibility = View.VISIBLE

    }

    private fun selectArticleTab() {
        initAllTabItem()
        tv_search_result_tab_article.setTextColor(ContextCompat.getColor(this, R.color.soft_blue))
        img_search_result_tab_article.visibility = View.VISIBLE
    }
}