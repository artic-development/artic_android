package com.android.artic.ui.search_result


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.artic.R
import com.android.artic.data.Archive
import com.android.artic.ui.adapter.archive.ArchiveListAdapter
import kotlinx.android.synthetic.main.fragment_archive_result.*

/**
 * none card archive list
 * */
class ArchiveListFragment(
    val data: List<Archive>
) : Fragment() {
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

        archiveListAdapter = ArchiveListAdapter(activity!!, data)
        rv_search_result_archive.adapter = archiveListAdapter
        rv_search_result_archive.layoutManager = LinearLayoutManager(activity!!)
    }

}
