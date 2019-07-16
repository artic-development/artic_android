package com.articrew.artic.ui.mypage.mypage


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager

import com.articrew.artic.R
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.adapter.deco.GridSpacesItemDecoration
import com.articrew.artic.ui.base.BaseFragment
import com.articrew.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_my_page_scrap.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject



/**
 * A simple [Fragment] subclass.
 *
 */
class MyPageScrapFragment : BaseFragment(R.layout.fragment_my_page_scrap) {
    private val repository: ArticRepository by inject()
    private lateinit var adapter: MyPageScrapAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {

            initialView()
            adapter = MyPageScrapAdapter(this, listOf())



            rv_mypage_scrap.adapter = adapter
            rv_mypage_scrap.layoutManager = GridLayoutManager(this, 2)
            rv_mypage_scrap.addItemDecoration(GridSpacesItemDecoration(this, 18.dpToPx(), 18.dpToPx()))
        }
    }

    private fun initialView() {
        rv_mypage_scrap.visibility=View.GONE
        mypage_scrap_empty_view.visibility=View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        activity?.run {
            repository.getMyPageScrap()
                .subscribe(
                    {
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
                    {
                        logger.error("my page scrap fragment get my page scrap error $it")
                        toast(R.string.network_error)
                    }
                ).apply { addDisposable(this) }
        }
    }
}






