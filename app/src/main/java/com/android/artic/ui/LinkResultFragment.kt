package com.android.artic.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.artic.R
import com.android.artic.ui.home.LinkOverviewRecyclerViewAdapter
import com.android.artic.ui.home.data.ListOverviewData
import kotlinx.android.synthetic.main.activity_link.*
import kotlinx.android.synthetic.main.fragment_link_result.*


class LinkResultFragment : Fragment() {

    lateinit var linkOverviewRecyclerViewAdapter: LinkOverviewRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_link_result, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var dataList: ArrayList<ListOverviewData> = ArrayList()
        dataList.add(
            ListOverviewData(
                "brunch.co.kr",
                "로고디자인을 위한 지식에 대한\n모든 것을 파헤치다",
                202,
                350,
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"
            )
        )
        dataList.add(
            ListOverviewData(
                "brunch.co.kr",
                "로고디자인을 위한 지식에 대한\n모든 것을 파헤치다",
                202,
                350,
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"
            )
        )

        dataList.add(
            ListOverviewData(
                "brunch.co.kr",
                "로고디자인을 위한 지식에 대한\n모든 것을 파헤치다",
                202,
                350,
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"
            )
        )

        linkOverviewRecyclerViewAdapter= LinkOverviewRecyclerViewAdapter(context!!, dataList)
        rv_search_result_link.adapter=linkOverviewRecyclerViewAdapter
        rv_search_result_link.layoutManager= LinearLayoutManager(context!!, RecyclerView.VERTICAL,false)


    }
}
