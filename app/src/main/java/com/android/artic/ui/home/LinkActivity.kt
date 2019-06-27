package com.android.artic.ui.home



import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.home.data.ListOverviewData
import kotlinx.android.synthetic.main.activity_link.*
import org.koin.android.ext.android.inject

class LinkActivity : AppCompatActivity() {
    private val repository: ArticRepository by inject()

    lateinit var linkOverviewRecyclerViewAdapter:LinkOverviewRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link)

        var dataList: ArrayList<ListOverviewData> = ArrayList()
        dataList.add(ListOverviewData(
            "brunch.co.kr",
            "로고디자인을 위한 지식에 대한\n모든 것을 파헤치다",
            202,
            350,
            "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"
        ))
        dataList.add(ListOverviewData(
            "brunch.co.kr",
            "로고디자인을 위한 지식에 대한\n모든 것을 파헤치다",
            202,
            350,
            "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"
        ))

        dataList.add(ListOverviewData(
            "brunch.co.kr",
            "로고디자인을 위한 지식에 대한\n모든 것을 파헤치다",
            202,
            350,
            "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"
        ))

        linkOverviewRecyclerViewAdapter= LinkOverviewRecyclerViewAdapter(this, dataList)
        rv_link_list.adapter=linkOverviewRecyclerViewAdapter
        rv_link_list.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)

    }

}

