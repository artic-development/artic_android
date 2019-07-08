package com.android.artic.ui.search_result


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.logger.Logger
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseFragment
import com.android.artic.ui.adapter.article.ArticleOverviewRecyclerViewAdapter
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_link_result.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LinkResultFragment(
    private val keyword: String
) : BaseFragment(R.layout.fragment_link_result) {
    private val repository: ArticRepository by inject()
    private val logger: Logger by inject()
    lateinit var adapter: ArticleOverviewRecyclerViewAdapter
    val searchCount = BehaviorSubject.createDefault(0)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter= ArticleOverviewRecyclerViewAdapter(activity!!, listOf(), false)
        rv_search_result_link.adapter=adapter
        rv_search_result_link.layoutManager= LinearLayoutManager(context!!, RecyclerView.VERTICAL,false)

        showEmptyView()

        repository.getSearchArticleList(
            keyword = keyword,
            successCallback = {
                // 필요하면 해당 subject 를 구독해서 정보 갱신됬을때 ui 변경할 수 있다.
                searchCount.onNext(it.size)
                if(it.isNotEmpty()) {
                    adapter.dataList = it
                    adapter.notifyDataSetChanged()

                    showListView()

                } else{
                    showEmptyView()
                }
            },
            failCallback = {
                logger.error("LinkResultFragment ${it.message}")
                toast(R.string.network_error)
                showEmptyView()
            }
        )
    }

    private fun showEmptyView() {
        rv_search_result_link.visibility=View.GONE
        link_result_empty.visibility=View.VISIBLE
    }

    private fun showListView() {
        rv_search_result_link.visibility=View.VISIBLE
        link_result_empty.visibility=View.GONE
    }

    override fun onResumeFragment() {
        super.onResumeFragment()

        // 필요하면 해당 subject 를 구독해서 정보 갱신됬을때 ui 변경할 수 있다.
        if (::adapter.isInitialized)
            searchCount.onNext(adapter.dataList.size)
    }
}
