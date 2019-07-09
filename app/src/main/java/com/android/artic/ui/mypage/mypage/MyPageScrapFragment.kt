package com.android.artic.ui.mypage.mypage


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.android.artic.R
import com.android.artic.data.Archive
import com.android.artic.logger.Logger
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseFragment
import com.android.artic.ui.adapter.deco.GridItemDecoration
import com.android.artic.ui.adapter.deco.GridSpacesItemDecoration
import com.android.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.view.*
import kotlinx.android.synthetic.main.fragment_my_page_me.*
import kotlinx.android.synthetic.main.fragment_my_page_scrap.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



/**
 * A simple [Fragment] subclass.
 *
 */
class MyPageScrapFragment : BaseFragment(R.layout.fragment_my_page_scrap) {
    private val repository: ArticRepository by inject()
    private lateinit var adapter: MyPageScrapAdapter
    private val logger: Logger by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {

            initialView()
            adapter = MyPageScrapAdapter(this, listOf())



            rv_mypage_scrap.adapter = adapter
            rv_mypage_scrap.layoutManager = GridLayoutManager(this, 2)
            rv_mypage_scrap.addItemDecoration(GridSpacesItemDecoration(this, 12.dpToPx(), 12.dpToPx()))
        }
    }

    private fun initialView() {
        rv_mypage_scrap.visibility=View.GONE
        mypage_scrap_empty_view.visibility=View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        activity?.run {
            repository.getMyPageScrap(
                successCallback = {
                    logger.log("scrap archive list")
                    if(it.isNotEmpty()) {
                        adapter.data = it
                        adapter.notifyDataSetChanged()
                        rv_mypage_scrap.visibility = View.VISIBLE
                        mypage_scrap_empty_view.visibility = View.GONE

                    } else{
                        rv_mypage_scrap.visibility=View.GONE
                        mypage_scrap_empty_view.visibility=View.VISIBLE
                    }
                },
                failCallback = {
                    toast(R.string.network_error)
                }
            )
        }
    }
}






