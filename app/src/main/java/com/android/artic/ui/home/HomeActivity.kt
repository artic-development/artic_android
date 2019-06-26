package com.android.artic.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    private val adapter: HomeCardAdapter by lazy { HomeCardAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // adapter, recyclerView setting
        rv_home_card.adapter = adapter
        rv_home_card.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        repository.getHomeCardList()
            .subscribe {
                adapter.data = it
                adapter.notifyDataSetChanged()
            }.apply { addDisposable(this) }
    }
}


