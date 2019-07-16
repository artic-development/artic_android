package com.articrew.artic.ui.detail_new_archive

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.adapter.archive.ArchiveListAdapter
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
    }

    override fun onResume() {
        super.onResume()

        // 새로운 아카이브 리스트 받아오는 통신
        repository.getNewArchiveList()
            .subscribe(
                {
                    adapter.dataList = it
                    adapter.notifyDataSetChanged()
                },
                {
                    logger.error("detail new archive activity get new archive list error")
                    toast(R.string.network_error)
                }
            ).apply { addDisposable(this) }


    }
}
