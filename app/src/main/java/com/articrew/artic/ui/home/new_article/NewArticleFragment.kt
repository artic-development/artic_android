package com.articrew.artic.ui.home.new_article

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.articrew.artic.R
import com.articrew.artic.data.Article
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.adapter.deco.HorizontalSpaceItemDecoration
import com.articrew.artic.ui.adapter.big_image_article.BigImageArticleAdapter
import com.articrew.artic.ui.base.BaseFragment
import com.articrew.artic.ui.new_article_link.NewArticleLinkActivity
import com.articrew.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_home_new_article.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class NewArticleFragment : BaseFragment(R.layout.fragment_home_new_article) {
    private val repository : ArticRepository by inject()
    private lateinit var articleCardAdapter: BigImageArticleAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            // 수민 추가 (액티비티 연결) -> 새로운 아티클을 누르면 새로운 아티클 리스트가 뜨는 화면으로 이동
            linear_fragment_home_new_article.setOnClickListener {
                var intent = Intent(this, NewArticleLinkActivity::class.java)

                startActivity(intent)
            }

            articleCardAdapter = BigImageArticleAdapter(this, listOf(), 0.8f)

            // 리사이클러뷰 아이템들 정렬
            LinearSnapHelper().attachToRecyclerView(rv_frag_home_new_article)
            rv_frag_home_new_article.adapter = articleCardAdapter
            rv_frag_home_new_article.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            // recyclerview space 조절
            var spacesItemDecoration =
                HorizontalSpaceItemDecoration(this, 10.dpToPx(), 20.dpToPx())
            rv_frag_home_new_article.addItemDecoration(spacesItemDecoration)

            // 데이터 갱신이 onResume 마다 될 필요가 없음.
            repository.getNewArticleList()
                .subscribe(
                    {
                        // 최신 10개의 article 만 가져온다!
                        it.take(10).let { cut->
                            articleCardAdapter.dataList = cut
                            articleCardAdapter.notifyDataSetChanged()
                        }
                    },
                    {
                        logger.error("new article fragment get new article list error")
                        toast(R.string.network_error)
                    }
                ).apply { addDisposable(this) }
        }
    }
}