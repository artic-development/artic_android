package com.android.artic.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.home.data.HomeCard
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    private val adapter: HomeCardAdapter by lazy { HomeCardAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // adapter, recyclerView setting
        rv_home_card.adapter = adapter
        rv_home_card.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Observable version
        //repository.getHomeCardList()
        //    .subscribe {
        //        adapter.data = it
        //        adapter.notifyDataSetChanged()
        //    }.apply { addDisposable(this) }

        repository.getHomeCardList().enqueue(object : Callback<List<HomeCard>> {
            override fun onFailure(call: Call<List<HomeCard>>, t: Throwable) {
                toast("홈 카드 업데이트 실패")
            }

            override fun onResponse(call: Call<List<HomeCard>>, response: Response<List<HomeCard>>) {
                response.body()?.let {
                    adapter.data = it
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}


