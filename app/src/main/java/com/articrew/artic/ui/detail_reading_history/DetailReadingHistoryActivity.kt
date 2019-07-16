package com.articrew.artic.ui.detail_reading_history

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.adapter.article.ArticleOverviewRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_detail_reading_history.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class DetailReadingHistoryActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    lateinit var adapter: ArticleOverviewRecyclerViewAdapter

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

//        repository.readingHistoryArticle(
//            successCallback = {
//
//                if (it.isEmpty()) {
//                    empty_view_act_detail_reading_history.visibility = View.VISIBLE
//                    rv_act_detail_reading_history.visibility = View.GONE
//                }
//                else {
//                    empty_view_act_detail_reading_history.visibility = View.GONE
//                    rv_act_detail_reading_history.visibility = View.VISIBLE
//                }
//
//                logger.log("recent reading article list")
//                adapter.dataList = it
//                adapter.notifyDataSetChanged()
//            },
//            errorCallback = {
//                toast(R.string.network_error)
//            }
//        )

        repository.readingHistoryArticle()
            .subscribe(
                {
                    if (it.isEmpty()) {
                        empty_view_act_detail_reading_history.visibility = View.VISIBLE
                        rv_act_detail_reading_history.visibility = View.GONE
                    }
                    else {
                        empty_view_act_detail_reading_history.visibility = View.GONE
                        rv_act_detail_reading_history.visibility = View.VISIBLE
                    }

                    logger.log("recent reading article list")
                    adapter.dataList = it
                    adapter.notifyDataSetChanged()
                },
                {
                    logger.error("detail reading history activity reading history article error")
                    toast(R.string.network_error)
                }
            ).apply { addDisposable(this) }
    }
}
