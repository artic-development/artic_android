package com.android.artic.ui.article_about

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.logger.Logger
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.article.ArticleActivity
import com.android.artic.ui.article_webview.ArticleWebViewActivity
import com.android.artic.util.defaultHolderOptions
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_article_about.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
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

        val articleId = intent.getIntExtra("articleId", -1)

        logger.log("ArticleAboutActivity articleId $articleId")

        if (articleId == -1) {
            //toast(R.string.network_error)
            //finish()
        }

        rv_article_about_another_article.adapter = adapter
        // 2x2 를 만들어줘야 하므로 데이터는 앞의 4개만 받아오자.
        rv_article_about_another_article.layoutManager = GridLayoutManager(this, 2)

        repository.getArticleWithExtra(
            articleId = articleId,
            successCallback = { article, archivePair ->
                txt_article_about_url.text = article.url
                // TODO 타이틀 길이가 너무 길면 안된다! 알아서 줄여주는 방안을 생각해야함.
                txt_article_about_title.text = title
                Glide.with(this@ArticleAboutActivity)
                    .load(article.title_img_url)
                    .apply(defaultHolderOptions)
                    .into(img_article_about)

                btn_article_about_read.setOnClickListener {
                    startActivity<ArticleWebViewActivity>("articleId" to articleId)
                }

                txt_article_about_archive_title.text = archivePair.second

                // 비동기 통신의 연속
                archivePair.first?.let { archiveId ->
                    logger.log("ArticleAboutActivity $archiveId")
                    repository.getArticleListGivenArchive(
                        archiveId = archiveId,
                        successCallback = {
                            it.take(4).let {cut->
                                adapter.data = cut
                                adapter.notifyDataSetChanged()
                            }
                            btn_article_about_show_all.setOnClickListener {
                                startActivity<ArticleActivity>(
                                    "archiveId" to archiveId,
                                    "categoryTitle" to "", // TODO category Title 도 받아와야하나?
                                    "archiveTitle" to archivePair.second
//                                    "archiveScraped" to
                                )
                            }
                        },
                        failCallback = {
                            toast(R.string.network_error)
                        }
                    )
                }
            },
            failCallback = {
                toast(R.string.network_error)
            }
        )
    }
}
