package com.android.artic.ui.home.new_article

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.ui.home.new_article.data.ArticleCardData
import com.android.artic.ui.GridSpacesItemDecoration
import com.android.artic.ui.detail_new_archive.DetailNewArchiveActivity
import com.android.artic.ui.home.new_article_link.NewArticleLinkActivity
import com.android.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_home_new_article.*
import kotlinx.android.synthetic.main.fragment_new_archive.*

class NewArticleFragment : Fragment() {

    private lateinit var articleCardAdapter: ArticleCardAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_new_article, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            // 수민 추가 (액티비티 연결) -> 새로운 아티클을 누르면 새로운 아티클 리스트가 뜨는 화면으로 이동
            linear_fragment_home_new_article.setOnClickListener {
                var intent = Intent(this, NewArticleLinkActivity::class.java)

                startActivity(intent)
            }

            // dataList
            var dataList = ArrayList<ArticleCardData>()
            dataList.add(ArticleCardData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrfEtpuKhHbz7VvGzYyPqGysRAaTGnunGAUpyDa8UNc4x1HdHM", "브랜딩은 린(lean)하게, 합리적인 선에서 어떻게 해야 할까?", "brunch.co.kr"))
            dataList.add(ArticleCardData("https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E", "브랜딩은 린(lean)하게, 합리적인 선에서 어떻게 해야 할까?", "brunch.co.kr"))
            dataList.add(ArticleCardData("https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E", "브랜딩은 린(lean)하게, 합리적인 선에서 어떻게 해야 할까?", "brunch.co.kr"))
            dataList.add(ArticleCardData("https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E", "브랜딩은 린(lean)하게, 합리적인 선에서 어떻게 해야 할까?", "brunch.co.kr"))
            dataList.add(ArticleCardData("https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E", "브랜딩은 린(lean)하게, 합리적인 선에서 어떻게 해야 할까?", "brunch.co.kr"))

            articleCardAdapter = ArticleCardAdapter(this, dataList)

            rv_frag_home_new_article.adapter = articleCardAdapter
            rv_frag_home_new_article.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            // recyclerview space 조절
            var spacesItemDecoration = GridSpacesItemDecoration(this, 10.dpToPx())
            rv_frag_home_new_article.addItemDecoration(spacesItemDecoration)
        }
    }
}