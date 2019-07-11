package com.android.artic.ui.detail_new_archive

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.base.BaseActivity
import com.android.artic.ui.adapter.archive.ArchiveListAdapter
import kotlinx.android.synthetic.main.activity_detail_new_archive.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class DetailNewArchiveActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    private val adapter: ArchiveListAdapter by lazy { ArchiveListAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_new_archive)

        rv_detail_new_archive_list.adapter = adapter
        rv_detail_new_archive_list.layoutManager = LinearLayoutManager(this)

        // 새로운 아카이브 리스트 받아오는 통신
//        repository.getNewArchiveList(
//            successCallback = {
//                adapter.dataList = it
//                adapter.notifyDataSetChanged()
//            },
//            failCallback = {
//                toast(R.string.network_error)
//            }
//        )
    }


    // onResume에다가 두면 계속,,,, 카테고리가 옆으로 이동해버리네
    override fun onResume() {
        super.onResume()

        // 새로운 아카이브 리스트 받아오는 통신
        repository.getNewArchiveList(
            successCallback = {
                adapter.dataList = it
                adapter.notifyDataSetChanged()
            },
            errorCallback = {
                toast(R.string.network_error)
            }
        )
    }
}
