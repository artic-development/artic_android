package com.android.artic.ui.detail_new_archive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.data.archive.ArchiveListData
import com.android.artic.ui.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.activity_detail_new_archive.*

class DetailNewArchiveActivity : AppCompatActivity() {

    lateinit var detailNewArchiveListAdapter: DetailNewArchiveListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_new_archive)

        var dataList : ArrayList<ArchiveListData> = ArrayList()

        var category : ArrayList<String> = ArrayList()
        category.add("UI / UX")
        category.add("브랜딩")

        dataList.add(ArchiveListData("PM과 디자이너가 서로 잘 소통하는 법을 알려주세", 13, category))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 13, category))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 13, category))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 13, category))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 13, category))

        detailNewArchiveListAdapter = DetailNewArchiveListAdapter(this, dataList)
        rv_act_detail_new_archive.adapter = detailNewArchiveListAdapter
        rv_act_detail_new_archive.layoutManager = LinearLayoutManager(this)
    }
}
