package com.android.artic.ui.archive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.data.Archive
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.adapter.archive.ArchiveListAdapter
import kotlinx.android.synthetic.main.activity_archive.*
import kotlinx.android.synthetic.main.activity_detail_new_archive.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * it must need categoryId:Int intent["categoryId"]
 * @author greedy0110
 * */
class ArchiveActivity : BaseActivity() {
    private var categoryId: Int = -1
    private val repository: ArticRepository by inject()
    private val adapter: ArchiveListAdapter by lazy { ArchiveListAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)

        rv_archive.adapter = adapter
        rv_archive.layoutManager = LinearLayoutManager(this)

        repository.getArchiveListGivenCategory(categoryId).enqueue(
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
