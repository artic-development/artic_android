package com.android.artic.ui.article_about

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.logger.Logger
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.article_webview.ArticleWebViewActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_article_about.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * it must need article id (intent["articleId"])
 * it must need archive id (intent["archiveId"])
 * @author greedy0110
 * */
class ArticleAboutActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    private val adapter: ArticleAdapter by lazy { ArticleAdapter(this, listOf()) }
    private val logger: Logger by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_about)

        val archiveId = intent.getIntExtra("archiveId", -1)
        val articleId = intent.getIntExtra("articleId", -1)

        logger.log("archiveId $archiveId , articleId $articleId")

        if (articleId == -1 || archiveId == -1) {
            //toast(R.string.network_error)
            //finish()
        }

        repository.getArchiveName(archiveId).enqueue(
            object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    toast(R.string.network_error)
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    response.body()?.let{
                        txt_article_about_archive_title.text = it
                    }
                }
            }
        )

        repository.getArticle(articleId = articleId,
            successCallback = {
                txt_article_about_url.text = it.url
                // TODO 타이틀 길이가 너무 길면 안된다! 알아서 줄여주는 방안을 생각해야함.
                txt_article_about_title.text = title
                Glide.with(this@ArticleAboutActivity)
                    .load(it.title_img_url)
                    .into(img_article_about)

                btn_article_about_read.setOnClickListener {
                    // TODO 아티클 읽기 구현해야함!
                    var intent = Intent(this@ArticleAboutActivity, ArticleWebViewActivity::class.java)

                    startActivity(intent)
                }
            },
            failCallback = {
                toast(R.string.network_error)
            })

        rv_article_about_another_article.adapter = adapter
        // 2x2 를 만들어줘야 하므로 데이터는 앞의 4개만 받아오자.
        rv_article_about_another_article.layoutManager = GridLayoutManager(this, 2)

        repository.getArticleListGivenArchive(archiveId).enqueue(
            object : Callback<List<Article>> {
                override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                    toast(R.string.network_error)
                }

                override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                    // 한번에 최대 4개의 아티클만 보일 것
                    response.body()?.take(4)?.let {
                        adapter.data = it
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )

        btn_article_about_show_all.setOnClickListener {
            // TODO archiveId 를 사용해서 아카이브 전체 보기 화면으로 넘어가야함.
        }

    }
}
