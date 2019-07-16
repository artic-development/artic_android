package com.articrew.artic.ui.home.new_archive


import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper

import com.articrew.artic.R
import com.articrew.artic.data.Archive
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.adapter.deco.HorizontalSpaceItemDecoration
import com.articrew.artic.ui.base.BaseFragment
import com.articrew.artic.ui.detail_new_archive.DetailNewArchiveActivity
import com.articrew.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_new_archive.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Show new-archive list
 * @author greedy0110
 */
class NewArchiveFragment : BaseFragment(R.layout.fragment_new_archive) {
    private val repository: ArticRepository by inject()
    private lateinit var adapter: ArchiveCardAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            adapter = ArchiveCardAdapter(this, listOf(
                Archive.createDummy(), Archive.createDummy(), Archive.createDummy()
            ))

            // @수민) 클릭 리스너
            linear_fragment_new_archive.setOnClickListener {
                var intent = Intent(this, DetailNewArchiveActivity::class.java)

                startActivity(intent)
            }

            LinearSnapHelper().attachToRecyclerView(rv_archive_card)
            rv_archive_card.adapter = adapter
            rv_archive_card.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            // recyclerview space 조절
            var spacesItemDecoration =
                HorizontalSpaceItemDecoration(this, 8.dpToPx(), 20.dpToPx())
            rv_archive_card.addItemDecoration(spacesItemDecoration)

            // recyclerview 데이터 뿌리기
            // 데이터 갱신이 onResume 마다 될 필요가 없음.
            repository.getNewArchiveList()
                .subscribe(
                    {
                        // 홈 화면에서는 10개의 카드만 보여줌
                        adapter.data = it.take(10)
                        adapter.notifyDataSetChanged()
                    },
                    {
                        logger.error("new archive fragment get new archive list error")
                        toast(R.string.network_error)
                    }
                ).apply { addDisposable(this) }
        }
    }
}
