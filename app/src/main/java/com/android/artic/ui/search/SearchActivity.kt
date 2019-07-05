package com.android.artic.ui.search

import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.GridSpacesItemDecoration
import com.android.artic.ui.search.data.RecommendWordData
import com.android.artic.util.dpToPx
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : BaseActivity() {

    private val repository: ArticRepository by inject()
    private val recommendWordAdapter: RecommendWordAdapter by lazy { RecommendWordAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        rv_search_recommend_word.adapter = recommendWordAdapter
        rv_search_recommend_word.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)

        // recyclerview space 조절
        var spacesItemDecoration = GridSpacesItemDecoration(this, 10.dpToPx(), 15.dpToPx())
//        spacesItemDecoration.
        rv_search_recommend_word.addItemDecoration(spacesItemDecoration)

        repository.getRecommendWordList().enqueue(
            object  : Callback<List<RecommendWordData>> {
                override fun onFailure(call: Call<List<RecommendWordData>>, t: Throwable) {
                    toast(R.string.network_error)
                }

                override fun onResponse(call: Call<List<RecommendWordData>>, response: Response<List<RecommendWordData>>) {
                    response.body()?.let {
                        recommendWordAdapter.dataList = it
                        recommendWordAdapter.notifyDataSetChanged()
                    }

                }
            }
        )
    }
}
