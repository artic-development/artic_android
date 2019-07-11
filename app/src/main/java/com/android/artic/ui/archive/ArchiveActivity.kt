package com.android.artic.ui.archive

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.logger.Logger
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.base.BaseActivity
import com.android.artic.ui.adapter.archive.ArchiveListAdapter
import kotlinx.android.synthetic.main.activity_archive.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

/**
 * it must need categoryId:Int intent["category_idx"]
 * it must need caetgoryName:Int intent["category_name"]
 * @author greedy0110
 * */
class ArchiveActivity : BaseActivity() {
//    private var categoryId: Int = -1
    private val logger: Logger by inject()
    private val repository: ArticRepository by inject()
    private val adapter: ArchiveListAdapter by lazy { ArchiveListAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)

        rv_archive.adapter = adapter
        rv_archive.layoutManager = LinearLayoutManager(this)

        // CategoryAdapter 에서 온 부분
        // CategoryAdapter에서 받은 category_id를 사용하여 해당 카테고리에 있는 아카이브 리스트들을 가져온다.




    }

    override fun onResume() {
        super.onResume()

        // onCreate에서 여기로 옮겨옴
        // category_id 받아오기
        val categoryId = intent.getIntExtra("category_idx", -1)
        val categoryName = intent.getStringExtra("category_name")

        if (categoryId == -1) {
            toast("카테고리 아이디를 받아오지 못했습니다.")
        }
        else { // 카테고리 아이디를 제대로 받아온 경우
            repository.getCategoryArchiveList(
                categoryId = categoryId,
                successCallback = {

                    if (it.isNotEmpty()) {
                        empty_view_act_archive.visibility = View.GONE
                        rv_archive.visibility = View.VISIBLE
                    }
                    else {
                        empty_view_act_archive.visibility = View.VISIBLE
                        rv_archive.visibility = View.GONE
                    }

                    adapter.dataList = it
                    adapter.notifyDataSetChanged()

                    logger.log(it.toString())
                },
                failCallback = {
                    toast(R.string.network_error)
                }
            )

            tv_archive_category_title.text = categoryName
        }
    }
}
