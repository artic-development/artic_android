package com.articrew.artic.ui.search_result


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

import com.articrew.artic.R
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseFragment
import com.articrew.artic.ui.adapter.archive.ArchiveListAdapter
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_archive_result.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

/**
 * none card archive list
 * */
class ArchiveListFragment(
    private val keyword: String
) : BaseFragment(R.layout.fragment_archive_result) {
    private val repository: ArticRepository by inject()
    lateinit var adapter: ArchiveListAdapter
    val searchNumber = BehaviorSubject.createDefault(0)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = ArchiveListAdapter(activity!!, listOf())
        rv_search_result_archive.adapter = adapter
        rv_search_result_archive.layoutManager = LinearLayoutManager(activity!!)

        rv_search_result_archive.visibility=View.GONE
        archive_result_empty.visibility=View.VISIBLE

        repository.getSearchArchiveList(keyword)
            .subscribe(
                {
                    if(it.isNotEmpty()) {
                        adapter.dataList = it
                        searchNumber.onNext(it.size)
                        adapter.notifyDataSetChanged()

                        rv_search_result_archive.visibility = View.VISIBLE
                        archive_result_empty.visibility = View.GONE
                    } else{
                        rv_search_result_archive.visibility=View.GONE
                        archive_result_empty.visibility=View.VISIBLE
                    }
                },
                {
                    logger.error("archive list fragment get search archive list error")
                    toast(R.string.network_error)
                }
            ).apply { addDisposable(this) }
    }

    override fun onResumeFragment() {
        super.onResumeFragment()

        activity?.run {
        }
    }
}
