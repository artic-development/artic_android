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
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.adapter.article.ArticleOverviewRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_link_result.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LinkResultFragment(
    val keyword: String
) : Fragment() {
    private val repository: ArticRepository by inject()
    lateinit var adapter: ArticleOverviewRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_link_result, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter= ArticleOverviewRecyclerViewAdapter(activity!!, listOf())
        rv_search_result_link.adapter=adapter
        rv_search_result_link.layoutManager= LinearLayoutManager(context!!, RecyclerView.VERTICAL,false)

        repository.getSearchArticleList(keyword).enqueue(
            object : Callback<List<Article>> {
                override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                    toast(R.string.network_error)
                }

                override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                    response.body()?.let {
                        adapter.dataList = it
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }
}
