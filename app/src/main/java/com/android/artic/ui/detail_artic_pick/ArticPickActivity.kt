package com.android.artic.ui.detail_artic_pick

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.base.BaseActivity
import com.android.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.android.artic.ui.adapter.big_image_article.BigImageArticleAdapter
import com.android.artic.util.dpToPx
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

        repository.getArticPickList(
            successCallback = {
                adapter.dataList = it
                adapter.notifyDataSetChanged()
            },
            errorCallback = {
                toast(R.string.network_error)
            }
        )

    }
}
