package com.android.artic.ui.new_article_link

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.base.BaseActivity
import com.android.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.android.artic.ui.adapter.big_image_article.BigImageArticleAdapter
import com.android.artic.util.dpToPx
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

        repository.getNewArticleList(
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
