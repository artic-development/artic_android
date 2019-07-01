package com.android.artic.ui.home

import android.os.Bundle
import com.android.artic.R
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.home.category_archive.CategoryArchiveFragment
import com.android.artic.ui.home.new_archive.NewArchiveFragment
import com.android.artic.ui.home.new_article.NewArticleFragment
import com.android.artic.ui.home.reading_history.ReadingHistoryFragment

/**
 * 1. Home Fragment 들을 관리할 것
 * 2. Fragment 들의 위치는 유동적으로 변경가능 하며 HomeActivity 가 관리할 것
 * 3. Search Activity 를 시작할 수 있어야
 * @author greedy0110
 * */
class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 일단 샘플로 홈화면 구성해봤어. fragmentManager add 를 사용해서 각자 만든 fragment 들을 계속 vertical linearLayout에 그려주는 형태로 구성함
        // HomeActivity 도 전체 Scroll 로 감싸서 구성해놨으니 이거 기반으로 작업 진행해도 괜찮을듯
        // 프래그먼트로 데이터를 던져줘야 하는 경우가 생기면 이것저것 찾아보는데, 난 그냥 생성자로 넘겨버리는 형태로 구성했음! - 승민
        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.container_home_fragments, NewArchiveFragment(), "new_archive_fragment")
        // 수민 추가
        transaction.add(R.id.container_home_fragments, NewArticleFragment(), "new_article_fragment")


        transaction.add(R.id.container_home_fragments,ReadingHistoryFragment(),"reading_history_fragment")
        val categoryName = "Design"
        transaction.add(R.id.container_home_fragments ,CategoryArchiveFragment(0, categoryName), "category_archive_$categoryName")


        transaction.commit()
    }
}