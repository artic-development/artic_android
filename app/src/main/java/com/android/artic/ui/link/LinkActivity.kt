package com.android.artic.ui.link



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.data.link.LinkOverviewData
import kotlinx.android.synthetic.main.activity_link.*
import org.koin.android.ext.android.inject

class LinkActivity : AppCompatActivity() {
    private val repository: ArticRepository by inject()

    lateinit var linkOverviewRecyclerViewAdapter: LinkOverviewRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link)

        var dataList: ArrayList<LinkOverviewData> = ArrayList()
        dataList.add(
            LinkOverviewData(
                "brunch.co.kr",
                "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                202,
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"
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

        dataList.add(
            LinkOverviewData(
                "brunch.co.kr",
                "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                202,
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"
            )
        )

        linkOverviewRecyclerViewAdapter= LinkOverviewRecyclerViewAdapter(this, dataList)
        rv_link_list.adapter=linkOverviewRecyclerViewAdapter
        rv_link_list.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)

    }

}

