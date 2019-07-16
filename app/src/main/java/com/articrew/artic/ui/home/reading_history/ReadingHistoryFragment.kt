package com.articrew.artic.ui.home.reading_history


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.articrew.artic.R
import com.articrew.artic.data.Article
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseFragment
import com.articrew.artic.ui.detail_reading_history.DetailReadingHistoryActivity
import kotlinx.android.synthetic.main.fragment_reading_history.*
import org.jetbrains.anko.support.v4.toast
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
class ReadingHistoryFragment : BaseFragment(R.layout.fragment_reading_history) {
    private val repository: ArticRepository by inject()
    private lateinit var adapter:ReadingHistoryAdapter

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
        }
    }

    // 홈으로 다시 돌아오면 최근 읽은 아티클이 다시 갱신되도록
    override fun onResume() {
        super.onResume()

        activity?.run {
            repository.readingHistoryArticle()
                .subscribe(
                    {
                        if (it.isEmpty()) {
                            supportFragmentManager.beginTransaction().remove(this@ReadingHistoryFragment).commitAllowingStateLoss()
                            return@subscribe
                        }
                        logger.log("recent reading article list")
                        adapter.dataList = it.take(5)
                        adapter.notifyDataSetChanged()
                    },
                    {
                        logger.error("reading history fragment reading history article error")
                        toast(R.string.network_error)
                    }
                ).apply { addDisposable(this) }
        }
    }

}
