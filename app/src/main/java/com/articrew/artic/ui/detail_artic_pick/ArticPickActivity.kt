package com.articrew.artic.ui.detail_artic_pick

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.articrew.artic.ui.adapter.big_image_article.BigImageArticleAdapter
import com.articrew.artic.util.dpToPx
import kotlinx.android.synthetic.main.activity_artic_pick.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject


class ArticPickActivity : BaseActivity() {

    private val repository : ArticRepository by inject()
    private val adapter by lazy{ BigImageArticleAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artic_pick)

        rv_artic_pick.adapter=adapter
        rv_artic_pick.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        var spaceItemDecoration = VerticalSpaceItemDecoration(this, 20.dpToPx())
        rv_artic_pick.addItemDecoration(spaceItemDecoration)

        repository.getArticPickList()
            .subscribe(
                {
                    adapter.dataList = it
                    adapter.notifyDataSetChanged()
                },
                {
                    logger.error("artic pick activity get artic pick list error")
                    toast(R.string.network_error)
                }
            ).apply { addDisposable(this) }

    }
}
