package com.android.artic.ui.article



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.adapter.article.ArticleOverviewRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_link.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * it must need archive id intent["archiveId"]
 * */
class ArticleActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    private var archiveId: Int = -1
    lateinit var adapter: ArticleOverviewRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link)



        adapter= ArticleOverviewRecyclerViewAdapter(this, listOf())
        rv_link_list.adapter = adapter
        rv_link_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        repository.getArticleListGivenArchive(archiveId).enqueue(
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

