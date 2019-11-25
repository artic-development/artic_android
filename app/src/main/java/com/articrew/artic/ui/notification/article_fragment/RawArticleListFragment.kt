package com.articrew.artic.ui.notification.article_fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.adapter.big_image_article.BigImageArticleAdapter
import com.articrew.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.articrew.artic.util.dpToPx
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_raw_article_list.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

/**
 * article list 데이터를 받고 해당 list 를 그려주는 fragment
 * @author greedy0110
 */
class RawArticleListFragment(
    private val title: String,
    private val data: List<Int>
) : Fragment() {
    private lateinit var adapter: BigImageArticleAdapter
    private val repository: ArticRepository by inject()

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

            adapter = BigImageArticleAdapter(this, listOf())

            rv_fragment_raw_article_list.adapter = adapter
            rv_fragment_raw_article_list.layoutManager = LinearLayoutManager(this)

            // recyclerview space 조절
            val spacesItemDecoration = VerticalSpaceItemDecoration(this, 20.dpToPx())
            rv_fragment_raw_article_list.addItemDecoration(spacesItemDecoration)

            Log.e("notinotif", "article id list = ${data}")

            Observable.just(data)
                .flatMapIterable { it }
                .concatMapEager { repository.getArticle(it) }
                .toList()
                .subscribe({
                    adapter.dataList = it
                    adapter.notifyDataSetChanged()
                }, {
                    toast(R.string.network_error)
                })
        }
    }
}
