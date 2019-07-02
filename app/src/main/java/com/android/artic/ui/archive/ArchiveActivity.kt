package com.android.artic.ui.archive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.data.archive.ArchiveListData
import kotlinx.android.synthetic.main.activity_archive.*

class ArchiveActivity : AppCompatActivity() {

    lateinit var archiveListAdapter: ArchiveListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)


        var dataList : ArrayList<ArchiveListData> = ArrayList()

        var category : ArrayList<String> = ArrayList()
        category.add("UI / UX")
        category.add("브랜딩")

        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 13, category))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, category))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, category))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, category))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, category))

        archiveListAdapter = ArchiveListAdapter(this, dataList)
        rv_archive_archive_list.adapter = archiveListAdapter
        rv_archive_archive_list.layoutManager = LinearLayoutManager(this)

        // spacing 조절
//        var spacesItemDecoration = SpacesItemDecoration(this, 10)
//        rv_archive_archive_list.addItemDecoration(spacesItemDecoration)
    }
}
