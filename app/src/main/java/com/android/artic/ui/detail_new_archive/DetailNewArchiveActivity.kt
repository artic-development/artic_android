package com.android.artic.ui.detail_new_archive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.artic.R
import com.android.artic.data.Archive
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.archive_none_card_fragment.ArchiveListFragment
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailNewArchiveActivity : AppCompatActivity() {
    private val repository: ArticRepository by inject()

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
                        supportFragmentManager.beginTransaction()
                            .add(R.id.container_detail_new_archive_list, ArchiveListFragment(it)).commit()
                    }
                }
            }
        )
    }
}
