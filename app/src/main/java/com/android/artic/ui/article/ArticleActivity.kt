package com.android.artic.ui.article

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
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
//    private var archiveId: Int = -1
    lateinit var adapter: ArticleOverviewRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

//        var archive_idx = intent.getIntExtra("archive_idx", -1)
//        var archive_idx = intent.getIntExtra("archive_idx", -1)


        tv_act_link_archive_title.text = intent.getStringExtra("categoryTitle")
        link_title.text = intent.getStringExtra("archiveTitle")
        var archive_idx = intent.getIntExtra("", -1)

        adapter= ArticleOverviewRecyclerViewAdapter(this, listOf())
        rv_link_list.adapter = adapter
        rv_link_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        repository.getArticleListGivenArchive(
            archiveId = archive_idx,
            successCallback = {
                adapter.dataList = it
                adapter.notifyDataSetChanged()
            },
            failCallback = {
                toast(R.string.network_error)
            }
        )
    }

}

