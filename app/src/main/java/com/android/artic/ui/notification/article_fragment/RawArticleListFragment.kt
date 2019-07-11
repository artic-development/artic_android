package com.android.artic.ui.notification.article_fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.ui.adapter.big_image_article.BigImageArticleAdapter
import com.android.artic.ui.adapter.deco.HorizontalSpaceItemDecoration
import com.android.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.android.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_raw_article_list.*

/**
 * article list 데이터를 받고 해당 list 를 그려주는 fragment
 * @author greedy0110
 */
class RawArticleListFragment(
    private val title: String,
    private val data: List<Article>
) : Fragment() {
    private lateinit var adapter: BigImageArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_raw_article_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            txt_fragment_raw_article_list_title.text = this@RawArticleListFragment.title
            adapter = BigImageArticleAdapter(this, data)

            rv_fragment_raw_article_list.adapter = adapter
            rv_fragment_raw_article_list.layoutManager = LinearLayoutManager(this)

            // recyclerview space 조절
            var spacesItemDecoration = VerticalSpaceItemDecoration(this, 20.dpToPx())
            rv_fragment_raw_article_list.addItemDecoration(spacesItemDecoration)
        }
    }
}
