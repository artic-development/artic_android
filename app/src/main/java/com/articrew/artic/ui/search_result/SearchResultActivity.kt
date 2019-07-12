package com.articrew.artic.ui.search_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.articrew.artic.R
import com.articrew.artic.logger.Logger
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.base.BaseOnPageChangeListener
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.search_result_tab.*
import org.koin.android.ext.android.inject

/**
 * it must need searchWord intent["searchKeyword"]
 *
 */
class SearchResultActivity : BaseActivity() {
    private var searchKeyword = ""
    private lateinit var adapter: SearchResultAdapter
    private lateinit var archiveListFragment: ArchiveListFragment
    private lateinit var linkResultFragment: LinkResultFragment
    private val searchNumberTask: (Int)-> Unit = {
        logger.log("search result number $it")
        search_result_number.text = it.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        searchKeyword = intent.getStringExtra("searchKeyword")
        search_result_category_name.text = searchKeyword

        archiveListFragment = ArchiveListFragment(searchKeyword)
        linkResultFragment = LinkResultFragment(searchKeyword)

        adapter = SearchResultAdapter(supportFragmentManager, listOf(archiveListFragment, linkResultFragment))
        search_result_viewpager.adapter = adapter
        search_result_viewpager.offscreenPageLimit=2

        search_result_viewpager.addOnPageChangeListener(
            object : BaseOnPageChangeListener(adapter) {
                // Page 가 변경될때 마다. 상단에 검색 결과가 변경, 반영되어야 한다.
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
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

        archiveListFragment.searchNumber.subscribe(searchNumberTask).apply { addDisposable(this) }
    }

    private fun selectArticleTab() {
        initAllTabItem()
        tv_search_result_tab_article.setTextColor(ContextCompat.getColor(this, R.color.soft_blue))
        img_search_result_tab_article.visibility = View.VISIBLE

        linkResultFragment.searchNumber.subscribe(searchNumberTask).apply { addDisposable(this) }
    }
}