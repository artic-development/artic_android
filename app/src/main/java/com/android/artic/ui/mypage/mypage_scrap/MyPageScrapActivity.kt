package com.android.artic.ui.mypage.mypage_scrap

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
import kotlinx.android.synthetic.main.activity_my_page_scrap.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        repository.getArticleListGivenArchive(
            archiveId = archiveId,
            successCallback = {
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
            failCallback = {
                toast(R.string.network_error)
            }
        )
    }
}
