package com.android.artic.ui.article

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.logger.Logger
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.base.BaseActivity
import com.android.artic.ui.adapter.article.ArticleOverviewRecyclerViewAdapter
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
        var archiveScraped = intent.getBooleanExtra("archiveScraped", false)

        link_btn_scrap.isChecked = archiveScraped // 아카이브 스크랩 여부에 따라 스크랩 버튼 바꿔줌


        adapter= ArticleOverviewRecyclerViewAdapter(this, listOf(), true)
        rv_link_list.adapter = adapter
        rv_link_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)


        // 아카이브 아이디로 아티클 리스트 받아오기
        repository.getArticleListGivenArchive(
            archiveId = archiveId,
            successCallback = {
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
            errorCallback = {
                toast(R.string.network_error)
            }
        )


        // @수민) 스크랩 버튼 통신
        link_btn_scrap.setOnClickListener {
            Log.v("soomin", "click scrap")

            repository.postArchiveScrap(
                archiveIdx = archiveId,
                successCallback = {
                },
                failCallback = {

                },
                statusCallback = { status, success, message ->
                    toast(message)
                }
            )
        }
    }

}

