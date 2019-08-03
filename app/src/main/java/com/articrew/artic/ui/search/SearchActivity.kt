package com.articrew.artic.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.adapter.deco.GridSpacesItemDecoration
import com.articrew.artic.util.dpToPx
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import android.view.inputmethod.EditorInfo
import com.articrew.artic.repository.remote.response.RecommendationResponse
import com.articrew.artic.ui.search.data.RecommendWordData
import com.articrew.artic.ui.search_result.SearchResultActivity
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import org.jetbrains.anko.startActivity


class SearchActivity : BaseActivity() {

    private val repository: ArticRepository by inject()
    private val recommendWordAdapter: RecommendWordAdapter by lazy { RecommendWordAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSearch()

        rv_search_recommend_word.adapter = recommendWordAdapter

        // layoutManager
        var layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START

        rv_search_recommend_word.layoutManager = layoutManager

        // recyclerview space 조절
        var spacesItemDecoration =
            GridSpacesItemDecoration(this, 10.dpToPx(), 15.dpToPx())
        rv_search_recommend_word.addItemDecoration(spacesItemDecoration)

        repository.getRecommendWordList()
            .subscribe(
                {
                    recommendWordAdapter.dataList = it
                    recommendWordAdapter.notifyDataSetChanged()
                },
                {
                    logger.error("search activity get recommend word list error")
                    toast(R.string.network_error)
                }
            ).apply { addDisposable(this) }
    }

    // @수민) 검색 기능 구현
    private fun setSearch() {
        et_act_search_search_word?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val keyword = v.text.toString()

                    if (keyword == "") {
                        toast("검색어를 입력해주세요")
                    }
                    else {
                        startActivity<SearchResultActivity>("searchKeyword" to keyword)
                    }

                    return true
                }
                return false
            }
        })

    }
}
