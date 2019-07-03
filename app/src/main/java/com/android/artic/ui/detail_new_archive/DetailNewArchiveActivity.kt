package com.android.artic.ui.detail_new_archive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.artic.R
import com.android.artic.data.Archive
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.adapter.archive.ArchiveListAdapter
import com.android.artic.ui.search_result.ArchiveListFragment
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailNewArchiveActivity : AppCompatActivity() {
    private val repository: ArticRepository by inject()
    private val adapter: ArchiveListAdapter by lazy { ArchiveListAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_new_archive)

        repository.getNewArchiveList().enqueue(
            object : Callback<List<Archive>> {
                override fun onFailure(call: Call<List<Archive>>, t: Throwable) {
                    toast(R.string.network_error)
                }

                override fun onResponse(call: Call<List<Archive>>, response: Response<List<Archive>>) {
                    response.body()?.let {
                        adapter.dataList = it
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }
}
