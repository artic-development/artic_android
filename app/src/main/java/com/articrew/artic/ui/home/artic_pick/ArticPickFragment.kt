package com.articrew.artic.ui.home.artic_pick

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
import com.articrew.artic.ui.detail_artic_pick.ArticPickActivity
import com.articrew.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_home_artic_pick.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticPickFragment : BaseFragment(R.layout.fragment_home_artic_pick) {
    private val repository : ArticRepository by inject()
    private lateinit var adapter: BigImageArticleAdapter

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

            adapter = BigImageArticleAdapter(this, listOf(), 0.8f)

            LinearSnapHelper().attachToRecyclerView(rv_frag_home_artic_pick)
            rv_frag_home_artic_pick.adapter = adapter
            rv_frag_home_artic_pick.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            // recyclerview space 조절
            var spacesItemDecoration =
                HorizontalSpaceItemDecoration(this, 10.dpToPx(), 20.dpToPx())
            rv_frag_home_artic_pick.addItemDecoration(spacesItemDecoration)

            // 데이터 갱신이 onResume 마다 될 필요가 없음.
            repository.getArticPickList()
                .subscribe(
                    {
                        // 추천 데이터가 없으면 프레그먼트를 제거하자!
                        if (it.isEmpty()) supportFragmentManager.beginTransaction().remove(this@ArticPickFragment).commitAllowingStateLoss()
                        adapter.dataList = it
                        adapter.notifyDataSetChanged()
                    },
                    {
                        supportFragmentManager.beginTransaction().remove(this@ArticPickFragment).commitAllowingStateLoss()
                        logger.error("artic pick fragment get artic pick list error")
                        toast(R.string.network_error)
                    }
                ).apply { addDisposable(this) }
        }
    }
}