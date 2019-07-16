package com.articrew.artic.ui.article_about

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.articrew.artic.R
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.article.ArticleActivity
import com.articrew.artic.ui.article_webview.ArticleWebViewActivity
import com.articrew.artic.util.defaultHolderOptions
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_article_about.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

/**
 * it must need article id (intent["articleId"])
 * @author greedy0110
 * */
class ArticleAboutActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    private val adapter: ArticleAdapter by lazy { ArticleAdapter(this, listOf()) }

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

        repository.getArticle(articleId)
            .subscribe(
                { article ->
                    txt_article_about_domain.text = article.domain_url
                    // TODO 타이틀 길이가 너무 길면 안된다! 알아서 줄여주는 방안을 생각해야함.
                    txt_article_about_title.text = article.title
                    Glide.with(this@ArticleAboutActivity)
                        .load(article.title_img_url)
                        .apply(defaultHolderOptions)
                        .into(img_article_about)

                    btn_article_about_read.setOnClickListener {
                        startActivity<ArticleWebViewActivity>("articleId" to articleId)
                    }

                    txt_article_about_archive_title.text = article.archive_title

                    // 비동기 통신의 연속
                    article.archive_id?.let { archiveId ->
                        logger.log("ArticleAboutActivity $archiveId")
                        repository.getArticleListGivenArchive(archiveId)
                            .subscribe( {
                                it.take(4).let {cut->
                                    adapter.data = cut
                                    adapter.notifyDataSetChanged()
                                }
                                btn_article_about_show_all.setOnClickListener {
                                    startActivity<ArticleActivity>(
                                        "archiveId" to archiveId,
                                        "categoryTitle" to "", // TODO category Title 도 받아와야하나?
                                        "archiveTitle" to article.archive_title
                                    )
                                }
                            },
                                {
                                    logger.error("article about activity get article with extra")
                                    toast(R.string.network_error)
                                }
                            ).apply { addDisposable(this) }
                    }
                },
                {
                    toast(R.string.network_error)
                }
            ).apply { addDisposable(this) }
    }
}
