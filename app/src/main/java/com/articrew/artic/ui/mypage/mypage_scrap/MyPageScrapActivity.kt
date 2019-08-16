package com.articrew.artic.ui.mypage.mypage_scrap

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.adapter.article.ArticleOverviewRecyclerViewAdapter
import com.articrew.artic.ui.edit_archive.EditArchiveActivity
import com.articrew.artic.util.PopupSystem
import kotlinx.android.synthetic.main.activity_my_page_scrap.*
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject


// TODO 내가 만든 아카이브 목록을 보여주는 Activity이다. 즉, Scrap 목록을 보여주는 화면이 아니다. 따라서 네이밍 수정이 필요하다.
class MyPageScrapActivity : BaseActivity() {
    private val repository :ArticRepository by inject()
    private val editArchiveRequestCode = 1234
    lateinit var adapter: ArticleOverviewRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_scrap)

        adapter= ArticleOverviewRecyclerViewAdapter(this, listOf(), true)
        val archiveId = intent.getIntExtra("archive_idx", -1)
        val archiveTitle = intent.getStringExtra("archive_title") // 아카이브 이름 받아오기
        val archiveImg = intent.getStringExtra("archive_img") // 아카이브 이름 받아오기

        mypage_scrap_link_title.text = archiveTitle // 아카이브 이름 설정

        rv_mypage_scrap_link.adapter=adapter
        rv_mypage_scrap_link.layoutManager=LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        repository.getArticleListGivenArchive(archiveId)
            .subscribe(
                {
                    adapter.dataList=it
                    adapter.notifyDataSetChanged()

                    mypage_scrap_link_num.text = it.size.toString()

                    if (it.size == 0) {
                        my_page_scrap_link_result_empty.visibility = View.VISIBLE
                        rv_mypage_scrap_link.visibility = View.GONE
                    }
                    else {
                        my_page_scrap_link_result_empty.visibility = View.GONE
                        rv_mypage_scrap_link.visibility = View.VISIBLE
                    }
                },
                {
                    toast("mypage scrap activity get article list given archive error")
                    toast(R.string.network_error)
                }
            ).apply { addDisposable(this) }

        btn_mypage_myarchive_modify.setOnClickListener {
            // 아카이브를 제거할 것인지, 이름을 변경할 것인지를 보여줄 팝업을 띄워준다.
            PopupSystem.show(this, R.layout.dlg_modify_archive,
                mapOf(
                    R.id.btn_dlg_archive_modify to {
                        // 아카이브 수정 activity로 이동해야한다.
                        startActivityForResult<EditArchiveActivity>(editArchiveRequestCode,
                            "archiveId" to archiveId,
                            "archiveTitle" to archiveTitle,
                            "archiveImg" to archiveImg
                        )
                    },
                    R.id.btn_dlg_archive_delete to  {
                        // 아카이브 제거 popup을 다시 띄워주어야 한다.
                        PopupSystem.show(this, R.layout.dlg_confirm_delete_archive,
                            mapOf(
                                R.id.btn_dlg_confirm_delete to {
                                    // 아카이브 제거 서버 통신을 해야한다.
                                    repository.deleteArchive(archiveId)
                                        .subscribe({
                                            toast(it)
                                            finish()
                                        }, {
                                            toast(R.string.network_error)
                                            logger.error("my page scrap activity delete archive error ${it.message}")
                                        })
                                },
                                R.id.btn_dlg_confirm_cancel to {
                                    // 팝업창을 종료하면 되니까 아무것도 하지않는다. (PopupSystem에서 알아서 종료시켜준다.)
                                }
                            )
                        )
                    }
                )
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            editArchiveRequestCode -> {
                when (resultCode) {
                    Activity.RESULT_OK -> { // 타이틀을 변경해주자.
                        mypage_scrap_link_title.text = data?.getStringExtra("archiveTitle") // 아카이브 이름 설정
                    }
                }
            }
        }
    }
}
