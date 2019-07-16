package com.articrew.artic.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.ScrollView
import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseFragment
import com.articrew.artic.ui.home.artic_pick.ArticPickFragment
import com.articrew.artic.ui.home.category_archive.CategoryArchiveFragment
import com.articrew.artic.ui.home.new_archive.NewArchiveFragment
import com.articrew.artic.ui.home.new_article.NewArticleFragment
import com.articrew.artic.ui.home.reading_history.ReadingHistoryFragment
import com.articrew.artic.ui.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

// TODO 비동기 데이터를 받아온 후 로드 & 순서대로 배치해야하는데 어캐?
/**
 * 1. Home Fragment 들을 관리할 것
 * 2. Fragment 들의 위치는 유동적으로 변경가능 하며 HomeFragment 가 관리할 것
 * 3. Search Activity 를 시작할 수 있어야
 * @author greedy0110
 * */
class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val repository: ArticRepository by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            // 일단 샘플로 홈화면 구성해봤어. fragmentManager add 를 사용해서 각자 만든 fragment 들을 계속 vertical linearLayout에 그려주는 형태로 구성함
            // HomeFragment 도 전체 Scroll 로 감싸서 구성해놨으니 이거 기반으로 작업 진행해도 괜찮을듯
            // 프래그먼트로 데이터를 던져줘야 하는 경우가 생기면 이것저것 찾아보는데, 난 그냥 생성자로 넘겨버리는 형태로 구성했음! - 승민
            val transaction = supportFragmentManager.beginTransaction()

            // 순서) 새로운 아카이브, 새로운 아티클, 최근 읽은 아티클, 아틱의 선택, 카테고리별
            transaction.add(R.id.container_home_fragments, NewArchiveFragment(), "new_archive_fragment") // 새로운 아카이브
            // 수민 추가
            transaction.add(R.id.container_home_fragments, NewArticleFragment(), "new_article_fragment") // 새로운 아티클


            transaction.add(
                R.id.container_home_fragments,
                ReadingHistoryFragment(),
                "reading_history_fragment"
            ) // 최근 읽은 아티클

            transaction.add(R.id.container_home_fragments, ArticPickFragment(), "artic_pick_fragment") // 아틱의 선택

            repository.getCategoryList()
                .subscribe(
                    {
                        val trans = supportFragmentManager.beginTransaction()
                        it.forEach { category ->
                            trans.add(R.id.container_home_fragments, CategoryArchiveFragment(category.id, category.name), category.name)
                        }
                        trans.commitAllowingStateLoss()
                    },
                    {
                        logger.error("home fragment category load error")
                        toast(R.string.network_error)
                    }
                )

            transaction.commitAllowingStateLoss()

            constraintLayout.setOnClickListener {
                var intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun requestTopScroll() {
        super.requestTopScroll()
        sv_fragment_home.isSmoothScrollingEnabled = true
        sv_fragment_home.smoothScrollTo(0,0)
    }
}