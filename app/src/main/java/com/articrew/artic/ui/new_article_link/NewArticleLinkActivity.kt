package com.articrew.artic.ui.new_article_link

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.articrew.artic.ui.adapter.big_image_article.BigImageArticleAdapter
import com.articrew.artic.util.dpToPx
import kotlinx.android.synthetic.main.activity_new_article_link.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject


class NewArticleLinkActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    private val adapter by lazy { BigImageArticleAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_article_link)

        rv_new_article_link.adapter = adapter
        rv_new_article_link.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        // recyclerview space 조절
        var spacesItemDecoration = VerticalSpaceItemDecoration(this, 20.dpToPx())
        rv_new_article_link.addItemDecoration(spacesItemDecoration)

        repository.getNewArticleList()
            .subscribe(
                {
                    adapter.dataList = it
                    adapter.notifyDataSetChanged()
                },
                {
                    logger.error("new article link activity get new article list error")
                    toast(R.string.network_error)
                }
        ).apply { addDisposable(this) }
    }
}
