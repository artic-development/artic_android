package com.android.artic.ui.home.new_article_link

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.home.new_article_link.NewArticleLinkListAdapter
import com.android.artic.ui.home.new_article_link.data.NewArticleLinkData
import kotlinx.android.synthetic.main.activity_new_article_link.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewArticleLinkActivity : AppCompatActivity() {
    private val repository: ArticRepository by inject()
    private val adapter by lazy {NewArticleLinkListAdapter(this, listOf())}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_article_link)


        rv_new_article_link.adapter=adapter
        rv_new_article_link.layoutManager=LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        repository.getNewArticleLinkList().enqueue(
            object : Callback<List<NewArticleLinkData>> {
                override fun onFailure(call: Call<List<NewArticleLinkData>>, t: Throwable) {
                    toast(R.string.network_error)
                }

                override fun onResponse(
                    call: Call<List<NewArticleLinkData>>,
                    response: Response<List<NewArticleLinkData>>
                ) {
                    response.body()?.let{
                        adapter.dataList=it
                        adapter.notifyDataSetChanged()
                    }
                }

            }
        )
    }
}
