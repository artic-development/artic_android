package com.android.artic.ui.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.adapter.article.ArticleOverviewRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_link.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        setContentView(R.layout.activity_link)

        tv_act_link_archive_title.text = intent.getStringExtra("categoryTitle")
        link_title.text = intent.getStringExtra("archiveTitle")
        archiveId = intent.getIntExtra("archiveId", -1)

        adapter= ArticleOverviewRecyclerViewAdapter(this, listOf())
        rv_link_list.adapter = adapter
        rv_link_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        repository.getArticleListGivenArchive(
            archiveId = archiveId,
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

