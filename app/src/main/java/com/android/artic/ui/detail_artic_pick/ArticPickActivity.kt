package com.android.artic.ui.detail_artic_pick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.adapter.big_image_article.BigImageArticleAdapter
import kotlinx.android.synthetic.main.activity_artic_pick.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArticPickActivity : BaseActivity() {

    private val repository : ArticRepository by inject()
    private val adapter by lazy{ BigImageArticleAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artic_pick)

        rv_artic_pick.adapter=adapter
        rv_artic_pick.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        repository.getArticPickList().enqueue(
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
