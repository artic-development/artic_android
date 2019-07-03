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
import com.android.artic.ui.adapter.article.ArticleOverviewRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_link_result.*


class LinkResultFragment(
    private val data: List<Article>
) : Fragment() {

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

        adapter= ArticleOverviewRecyclerViewAdapter(context!!, data)
        rv_search_result_link.adapter=adapter
        rv_search_result_link.layoutManager= LinearLayoutManager(context!!, RecyclerView.VERTICAL,false)


    }
}
