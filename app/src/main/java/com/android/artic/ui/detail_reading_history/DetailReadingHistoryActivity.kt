package com.android.artic.ui.detail_reading_history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.link.LinkOverviewData
import com.android.artic.ui.link.LinkOverviewRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_detail_reading_history.*
import kotlinx.android.synthetic.main.activity_link.*

class DetailReadingHistoryActivity : AppCompatActivity() {

    lateinit var linkOverviewRecyclerViewAdapter: LinkOverviewRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_reading_history)

        var dataList: ArrayList<LinkOverviewData> = ArrayList()
        dataList.add(
            LinkOverviewData(
                "brunch.co.kr",
                "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                202,
                "https://image.musinsa.com/mfile_s01/2019/04/13/728e95b288da3ab4767e1a4ca5e568cd163033.jpg"
            )
        )
        dataList.add(
            LinkOverviewData(
                "brunch.co.kr",
                "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                202,
                "https://image.musinsa.com/mfile_s01/2019/04/13/728e95b288da3ab4767e1a4ca5e568cd163033.jpg"
            )
        )

        dataList.add(
            LinkOverviewData(
                "brunch.co.kr",
                "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                202,
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"
            )
        )

        linkOverviewRecyclerViewAdapter= LinkOverviewRecyclerViewAdapter(this, dataList)
        rv_act_detail_reading_history.adapter=linkOverviewRecyclerViewAdapter
        rv_act_detail_reading_history.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
    }
}
