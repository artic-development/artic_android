package com.android.artic.ui.home.artic_pick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.home.artic_pick.data.ArticPickData
import kotlinx.android.synthetic.main.activity_artic_pick.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ArticPickActivity : AppCompatActivity() {

    private val repository : ArticRepository by inject()
    private val adapter by lazy{ArticPickAdapter(this, listOf())}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artic_pick)

        rv_artic_pick.adapter=adapter
        rv_artic_pick.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        repository.getArticPickList().enqueue(
            object : Callback<List<ArticPickData>> {
                override fun onFailure(call: Call<List<ArticPickData>>, t: Throwable) {
                    toast(R.string.network_error)
                }

                override fun onResponse(call: Call<List<ArticPickData>>, response: Response<List<ArticPickData>>) {
                    response.body()?.let {
                        adapter.dataList = it
                        adapter.notifyDataSetChanged()
                    }

                }
            }
        )

    }
}
