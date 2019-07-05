package com.android.artic.ui.new_article_link

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.HorizontalSpaceItemDecoration
import com.android.artic.ui.VerticalSpaceItemDecoration
import com.android.artic.ui.adapter.big_image_article.BigImageArticleAdapter
import com.android.artic.util.dpToPx
import kotlinx.android.synthetic.main.activity_new_article_link.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        repository.getDummyArticleList().enqueue(
            object : Callback<List<Article>> {
                override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                    toast(R.string.network_error)
                }

                override fun onResponse(
                    call: Call<List<Article>>,
                    response: Response<List<Article>>
                ) {
                    response.body()?.let{
                        adapter.dataList = it
                        adapter.notifyDataSetChanged()
                    }
                }

            }
        )
    }
}
