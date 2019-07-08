package com.android.artic.ui.detail_reading_history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.logger.Logger
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.adapter.article.ArticleOverviewRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_detail_reading_history.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailReadingHistoryActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    lateinit var adapter: ArticleOverviewRecyclerViewAdapter
    private val logger: Logger by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_reading_history)


        adapter= ArticleOverviewRecyclerViewAdapter(this, listOf(), false)
        rv_act_detail_reading_history.adapter=adapter
        rv_act_detail_reading_history.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)

//        repository.getReadingHistoryArticleList().enqueue(
//            object : Callback<List<Article>> {
//                override fun onFailure(call: Call<List<Article>>, t: Throwable) {
//                    toast(R.string.network_error)
//                }
//
//                override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
//                    response.body()?.let {
//                        adapter.dataList = it
//                        adapter.notifyDataSetChanged()
//                    }
//                }
//            }
//        )

        repository.readingHistoryArticle(
            successCallback = {
                logger.log("recent reading article list")
                adapter.dataList = it
                adapter.notifyDataSetChanged()
            },
            failCallback = {
                toast(R.string.network_error)
            }
        )
    }
}
