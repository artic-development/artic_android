package com.android.artic.ui.search_result


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.artic.R
import com.android.artic.data.Archive
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.adapter.archive.ArchiveListAdapter
import kotlinx.android.synthetic.main.fragment_archive_result.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * none card archive list
 * */
class ArchiveListFragment(
    val keyword: String
) : Fragment() {
    private val repository: ArticRepository by inject()
    lateinit var adapter: ArchiveListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_archive_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = ArchiveListAdapter(activity!!, listOf())
        rv_search_result_archive.adapter = adapter
        rv_search_result_archive.layoutManager = LinearLayoutManager(activity!!)

        repository.getSearchArchiveList(keyword).enqueue(
            object : Callback<List<Archive>> {
                override fun onFailure(call: Call<List<Archive>>, t: Throwable) {
                    toast(R.string.network_error)
                }

                override fun onResponse(call: Call<List<Archive>>, response: Response<List<Archive>>) {
                    response.body()?.let {
                        adapter.dataList = it
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }

}
