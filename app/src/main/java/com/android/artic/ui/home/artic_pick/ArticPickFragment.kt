package com.android.artic.ui.home.artic_pick

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.home.new_article.ArticleCardAdapter
import com.android.artic.ui.HorizontalSpaceItemDecoration
import com.android.artic.ui.detail_artic_pick.ArticPickActivity
import com.android.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_home_artic_pick.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticPickFragment : Fragment() {
    private val repository : ArticRepository by inject()
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

            articleCardAdapter = ArticleCardAdapter(this, listOf())

            rv_frag_home_artic_pick.adapter = articleCardAdapter
            rv_frag_home_artic_pick.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            // recyclerview space 조절
            var spacesItemDecoration = HorizontalSpaceItemDecoration(this, 10.dpToPx(), 20.dpToPx())
            rv_frag_home_artic_pick.addItemDecoration(spacesItemDecoration)

            repository.getArticPickList().enqueue(
                object : Callback<List<Article>> {
                    override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                        toast(R.string.network_error)
                    }

                    override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                        response.body()?.let {
                            articleCardAdapter.dataList = it
                            articleCardAdapter.notifyDataSetChanged()
                        }
                    }
                }
            )
        }
    }
}