package com.android.artic.ui.home.reading_history


import android.os.Bundle
import android.service.voice.AlwaysOnHotwordDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.home.reading_history.data.HistoryData
import kotlinx.android.synthetic.main.fragment_new_archive.*
import kotlinx.android.synthetic.main.fragment_reading_history.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 *
 */
class ReadingHistoryFragment : Fragment() {
    private val repository: ArticRepository by inject()
    private lateinit var adapter:ReadingHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reading_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            adapter = ReadingHistoryAdapter(this, listOf())

            rv_reading_history.adapter = adapter
            rv_reading_history.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

            repository.getReadingHistoryArchiveList().enqueue(
                object : Callback<List<HistoryData>> {
                    override fun onFailure(call: Call<List<HistoryData>>, t: Throwable) {
                        toast(R.string.network_error)
                    }

                    override fun onResponse(
                        call: Call<List<HistoryData>>,
                        response: Response<List<HistoryData>>
                    ) {
                        response.body()?.let {
                            adapter.dataList = it
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            )
        }
    }

}
