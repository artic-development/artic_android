package com.articrew.artic.ui.mypage.mypage_scrap

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.adapter.article.ArticleOverviewRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_my_page_scrap.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject


class MyPageScrapActivity : BaseActivity() {
    private val repository :ArticRepository by inject()
//    private var archiveId : Int=-1
    lateinit var adapter: ArticleOverviewRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_scrap)

        adapter= ArticleOverviewRecyclerViewAdapter(this, listOf(), true)
        var archiveId = intent.getIntExtra("archive_idx", -1)
        var archiveTitle = intent.getStringExtra("archive_title") // 아카이브 이름 받아오기

        mypage_scrap_link_title.text = archiveTitle // 아카이브 이름 설정

        rv_mypage_scrap_link.adapter=adapter
        rv_mypage_scrap_link.layoutManager=LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        repository.getArticleListGivenArchive(archiveId)
            .subscribe(
                {
                    adapter.dataList=it
                    adapter.notifyDataSetChanged()

                    mypage_scrap_link_num.text = it.size.toString()

                    if (it.size == 0) {
                        my_page_scrap_link_result_empty.visibility = View.VISIBLE
                        rv_mypage_scrap_link.visibility = View.GONE
                    }
                    else {
                        my_page_scrap_link_result_empty.visibility = View.GONE
                        rv_mypage_scrap_link.visibility = View.VISIBLE
                    }
                },
                {
                    toast("mypage scrap activity get article list given archive error")
                    toast(R.string.network_error)
                }
            ).apply { addDisposable(this) }
    }
}
