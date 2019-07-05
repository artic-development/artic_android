package com.android.artic.ui.home.reading_history


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.detail_reading_history.DetailReadingHistoryActivity
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
            // 수민 추가 (액티비티 연결) -> 최근 읽은 아티클을 누르면 최근 읽은 아티클 리스가 뜨는 화면으로 이동
            relative_fragment_reading_history.setOnClickListener {
                var intent = Intent(this, DetailReadingHistoryActivity::class.java)

                startActivity(intent)
            }

            adapter = ReadingHistoryAdapter(this, listOf())

            rv_reading_history.adapter = adapter
            rv_reading_history.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

            repository.getReadingHistoryArticleList().enqueue(
                object : Callback<List<Article>> {
                    override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                        toast(R.string.network_error)
                    }

                    override fun onResponse(
                        call: Call<List<Article>>,
                        response: Response<List<Article>>
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
