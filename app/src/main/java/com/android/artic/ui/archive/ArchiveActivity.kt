package com.android.artic.ui.archive

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.adapter.archive.ArchiveListAdapter
import com.android.artic.ui.article_webview.ArticleWebViewActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_archive.*
import kotlinx.android.synthetic.main.activity_article_about.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

/**
 * it must need categoryId:Int intent["category_idx"]
 * it must need caetgoryName:Int intent["category_name"]
 * @author greedy0110
 * */
class ArchiveActivity : BaseActivity() {
//    private var categoryId: Int = -1
    private val repository: ArticRepository by inject()
    private val adapter: ArchiveListAdapter by lazy { ArchiveListAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)

        rv_archive.adapter = adapter
        rv_archive.layoutManager = LinearLayoutManager(this)

        // CategoryAdapter 에서 온 부분
        // CategoryAdapter에서 받은 category_id를 사용하여 해당 카테고리에 있는 아카이브 리스트들을 가져온다.

        // category_id 받아오기
        val categoryId = intent.getIntExtra("category_idx", -1)
        val categoryName = intent.getStringExtra("category_name")

        if (categoryId == -1) {
            toast("카테고리 아이디를 받아오지 못했습니다.")
        }
        else { // 카테고리 아이디를 제대로 받아온 경우
            repository.getCategoryArchiveList(categoryId = categoryId,
                successCallback = {
                    adapter.dataList = it
                    adapter.notifyDataSetChanged()
                },
                failCallback = {
                    toast(R.string.network_error)
                }
            )

            tv_archive_category_title.text = categoryName
        }
    }
}
