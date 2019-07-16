package com.articrew.artic.ui.article

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.adapter.article.ArticleOverviewRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_article.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

/**
 * it must need archive id intent["archiveId"]: Int
 * it must need category title intent["categoryTitle"]: String
 * it must need category title intent["archiveTitle]: String
 * */
class ArticleActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    private var archiveId: Int = -1
    lateinit var adapter: ArticleOverviewRecyclerViewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        tv_act_link_archive_title.text = intent.getStringExtra("categoryTitle")
        link_title.text = intent.getStringExtra("archiveTitle")
        archiveId = intent.getIntExtra("archiveId", -1)

        adapter= ArticleOverviewRecyclerViewAdapter(this, listOf(), true)
        rv_link_list.adapter = adapter
        rv_link_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        // 스크랩 여부를 서버에서 쿼리해온다.
        repository.getArchiveIsScrap(archiveId)
            .subscribe {
                logger.log("get archive is scarp $it")
                link_btn_scrap.isChecked = it

                // 스크랩 여부를 받아왔을때만 정상적으로 서버 통신할 수 있도록 구성한다.
                // @수민) 스크랩 버튼 통신
                link_btn_scrap.setOnClickListener {
                    logger.log("click scrap")
                    repository.postArchiveScrap(archiveId)
                        .subscribe {
                            toast(it)
                        }.apply { addDisposable(this) }
                }
            }.apply { addDisposable(this) }
    }


    override fun onResume() {
        super.onResume()

        // 아카이브 아이디로 아티클 리스트 받아오기
        repository.getArticleListGivenArchive(archiveId)
            .subscribe(
                {
                    if (it.isNotEmpty()) {
                        empty_view_act_article.visibility = View.GONE
                        rv_link_list.visibility = View.VISIBLE
                    }
                    else {
                        empty_view_act_article.visibility = View.VISIBLE
                        rv_link_list.visibility = View.GONE
                    }
                    adapter.dataList = it
                    adapter.notifyDataSetChanged()
                },
                {
                    logger.error("article activity get article list given archive error")
                    toast(R.string.network_error)
                }
            ).apply { addDisposable(this) }
    }
}

