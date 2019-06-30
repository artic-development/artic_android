package com.android.artic.ui.search_result


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.artic.R
import com.android.artic.ui.archive.ArchiveListAdapter
import com.android.artic.data.archive.ArchiveListData
import kotlinx.android.synthetic.main.fragment_archive_result.*


class ArchiveResultFragment : Fragment() {

    lateinit var archiveListAdapter: ArchiveListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_archive_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var dataList : ArrayList<ArchiveListData> = ArrayList()
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, 13))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, 13))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, 13))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, 13))
        dataList.add(ArchiveListData("린 브랜딩을 어떻게 시작해야 할까?", 350, 13))

        archiveListAdapter = ArchiveListAdapter(context!!, dataList)
        rv_search_result_archive.adapter = archiveListAdapter
        rv_search_result_archive.layoutManager = LinearLayoutManager(context!!)
    }

}
