package com.android.artic.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.artic.R
import com.android.artic.ui.GridSpacesItemDecoration
import com.android.artic.ui.search.data.RecommendWordData
import com.android.artic.util.dpToPx
import com.android.artic.util.pxToDp
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    lateinit var recommendWordAdapter: RecommendWordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var dataList : ArrayList<RecommendWordData> = ArrayList()
        dataList.add(RecommendWordData("UI/UX 디자인"))
        dataList.add(RecommendWordData("BX 디자인"))
        dataList.add(RecommendWordData("브랜딩"))
        dataList.add(RecommendWordData("서비스 기획"))
        dataList.add(RecommendWordData("편집 디자인"))
        dataList.add(RecommendWordData("안드로이드"))
        dataList.add(RecommendWordData("iOS"))
        dataList.add(RecommendWordData("SOPT"))

        recommendWordAdapter = RecommendWordAdapter(this, dataList)
        rv_search_recommend_word.adapter = recommendWordAdapter
        rv_search_recommend_word.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)

        // recyclerview space 조절
        var spacesItemDecoration = GridSpacesItemDecoration(this, 15.dpToPx())
        rv_search_recommend_word.addItemDecoration(spacesItemDecoration)
    }
}
