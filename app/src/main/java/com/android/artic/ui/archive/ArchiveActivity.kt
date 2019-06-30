package com.android.artic.ui.archive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.ui.archive.data.ArchiveListData
import com.android.artic.ui.search.SpacesItemDecoration
import kotlinx.android.synthetic.main.activity_archive.*
import kotlinx.android.synthetic.main.activity_search.*

class ArchiveActivity : AppCompatActivity() {

    lateinit var archiveListAdapter: ArchiveListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)


        var dataList : ArrayList<ArchiveListData> = ArrayList()
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, 13))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, 13))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, 13))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, 13))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, 13))

        archiveListAdapter = ArchiveListAdapter(this, dataList)
        rv_archive_archive_list.adapter = archiveListAdapter
        rv_archive_archive_list.layoutManager = LinearLayoutManager(this)

        // spacing 조절
//        var spacesItemDecoration = SpacesItemDecoration(this, 10)
//        rv_archive_archive_list.addItemDecoration(spacesItemDecoration)
    }
}
