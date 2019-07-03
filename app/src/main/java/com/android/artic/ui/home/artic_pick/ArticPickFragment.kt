package com.android.artic.ui.home.artic_pick

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.ui.home.new_article.ArticleCardAdapter
import com.android.artic.ui.home.new_article.data.ArticleCardData
import com.android.artic.ui.GridSpacesItemDecoration
import com.android.artic.ui.HorizontalSpaceItemDecoration
import com.android.artic.ui.home.new_article_link.NewArticleLinkActivity
import com.android.artic.util.dpToPx
import com.android.artic.util.pxToDp
import kotlinx.android.synthetic.main.fragment_home_artic_pick.*
import kotlinx.android.synthetic.main.fragment_home_new_article.*

class ArticPickFragment : Fragment() {

    private lateinit var articleCardAdapter: ArticleCardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_artic_pick, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            // 수민 추가 (액티비티 연결) -> 새로운 아티클을 누르면 새로운 아티클 리스트가 뜨는 화면으로 이동
            linear_fragment_home_artic_pick.setOnClickListener {
                var intent = Intent(this, ArticPickActivity::class.java)

                startActivity(intent)
            }

            // dataList
            var dataList = ArrayList<ArticleCardData>()
            dataList.add(ArticleCardData("https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E", "디자이너가 알아두면 좋은 인하우스와 에이전시의 차이점", "brunch.co.kr"))
            dataList.add(ArticleCardData("https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E", "디자이너가 알아두면 좋은 인하우스와 에이전시의 차이점", "brunch.co.kr"))
            dataList.add(ArticleCardData("https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E", "디자이너가 알아두면 좋은 인하우스와 에이전시의 차이점", "brunch.co.kr"))
            dataList.add(ArticleCardData("https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E", "디자이너가 알아두면 좋은 인하우스와 에이전시의 차이점", "brunch.co.kr"))
            dataList.add(ArticleCardData("https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E", "디자이너가 알아두면 좋은 인하우스와 에이전시의 차이점", "brunch.co.kr"))

            articleCardAdapter = ArticleCardAdapter(this, dataList)

            rv_frag_home_artic_pick.adapter = articleCardAdapter
            rv_frag_home_artic_pick.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            // recyclerview space 조절
            var spacesItemDecoration = HorizontalSpaceItemDecoration(this, 10.dpToPx(), 20.dpToPx())
            rv_frag_home_artic_pick.addItemDecoration(spacesItemDecoration)
        }
    }
}