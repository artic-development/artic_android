package com.android.artic.ui.mypage


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.GridItemDecoration
import com.android.artic.ui.home.reading_history.data.HistoryData
import com.android.artic.ui.mypage.data.MyPageScrapData
import kotlinx.android.synthetic.main.fragment_my_page_scrap.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



/**
 * A simple [Fragment] subclass.
 *
 */
class MyPageScrapFragment : Fragment() {
    private val repository: ArticRepository by inject()
    private lateinit var adapter: MyPageScrapAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_page_scrap, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            adapter = MyPageScrapAdapter(this, listOf())



            rv_mypage_scrap.adapter = adapter
            rv_mypage_scrap.layoutManager = GridLayoutManager(this, 2)
            rv_mypage_scrap.addItemDecoration(GridItemDecoration(5,2))


            repository.getMyPageScrap().enqueue(
                object : Callback<List<MyPageScrapData>> {
                    override fun onFailure(call: Call<List<MyPageScrapData>>, t: Throwable) {
                        toast(R.string.network_error)
                    }

                    override fun onResponse(
                        call: Call<List<MyPageScrapData>>,
                        response: Response<List<MyPageScrapData>>
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






