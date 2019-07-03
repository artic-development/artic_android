package com.android.artic.ui.home.new_archive


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.detail_new_archive.DetailNewArchiveActivity
import com.android.artic.ui.home.new_archive.data.ArchiveCardData
import kotlinx.android.synthetic.main.fragment_new_archive.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Show new-archive list
 * @author greedy0110
 */
class NewArchiveFragment : Fragment() {
    private val repository: ArticRepository by inject()
    private lateinit var adapter: ArchiveCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_archive, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            // 수민 추가 (액티비티 연결) -> 새로운 아카이브를 누르면 새로운 아카이브 리스트가 뜨는 화면으로 이동
            linear_fragment_new_archive.setOnClickListener {
                var intent = Intent(this, DetailNewArchiveActivity::class.java)

                startActivity(intent)
            }

            adapter = ArchiveCardAdapter(this, listOf())

            rv_archive_card.adapter = adapter
            rv_archive_card.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            repository.getNewArchiveList().enqueue(
                object :Callback<List<ArchiveCardData>> {
                    override fun onFailure(call: Call<List<ArchiveCardData>>, t: Throwable) {
                        toast(R.string.network_error)
                    }

                    override fun onResponse(
                        call: Call<List<ArchiveCardData>>,
                        response: Response<List<ArchiveCardData>>
                    ) {
                        response.body()?.let {
                            adapter.data = it
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            )
        }
    }
}
