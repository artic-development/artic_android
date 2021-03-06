package com.articrew.artic.ui.collect_archive

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.articrew.artic.R
import com.articrew.artic.data.Archive
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.adapter.deco.HorizontalSpaceItemDecoration
import com.articrew.artic.ui.new_archive.MakeNewArchiveActivity
import com.articrew.artic.util.dpToPx
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_put_archive.*
import kotlinx.android.synthetic.main.fragment_my_page_me.*
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectArchiveDialogFragment : BottomSheetDialogFragment() {

    private val repository: ArticRepository by inject()
    private val collectArchiveListAdapter: CollectArchiveListAdapter by lazy { CollectArchiveListAdapter(context!!, this, listOf()) }
    private var selectArticleId = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_put_archive, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
        setButtonListener()

        selectArticleId = arguments!!.getInt("article_idx")
    }

    // @수민) Button Listener 설정
    private fun setButtonListener() {
        // @수민) 완료 버튼
        btn_rv_dialog_put_archive_complete.setOnClickListener {
            if (btn_rv_dialog_put_archive_complete.text.toString() == "취소") {
                dismiss()
            }
            else if (btn_rv_dialog_put_archive_complete.text.toString() == "완료") {
                repository.postCollectArticleInArchive(
                    archiveIdx = collectArchiveListAdapter.selectArchiveId,
                    articleIdx = selectArticleId,
                    successCallback = {
                        toast("success storage")
                    },
                    failCallback = {
                      //  toast("fail storage")
                    },
                    statusCallback = { status, success, message ->
                        toast(message)
                        dismiss()
                    }
                )

            }
        }

        // @수민) 플러스 버튼
        ibtn_dialog_put_archive_new_archive.setOnClickListener {
            var intent = Intent(ctx, MakeNewArchiveActivity::class.java)

            startActivity(intent)
        }

        // @수민) 내 아카이브 리스트가 없을 때, 아카이브 만들기를 누르면 아카이브 만드는 화면으로 이동
        linear_dialog_put_archive_make_new_archive.setOnClickListener {
            var intent = Intent(ctx, MakeNewArchiveActivity::class.java)

            startActivity(intent)
        }
    }

    // @수민) RecyclerView 설정
    private fun setRecyclerView() {
        rv_dialog_put_archive_my_archive_list.adapter = collectArchiveListAdapter
        rv_dialog_put_archive_my_archive_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        var spacesItemDecoration =
            HorizontalSpaceItemDecoration(ctx, 16.dpToPx(), 20.dpToPx())
        rv_dialog_put_archive_my_archive_list.addItemDecoration(spacesItemDecoration)

        repository.getMyPageMe(
            successCallback = {
                if(it.isNotEmpty()) {
                    collectArchiveListAdapter.dataList = it
                    collectArchiveListAdapter.notifyDataSetChanged()

                    linear_dialog_put_archive_make_new_archive.visibility=View.GONE
                }
                else{ // 내 아카이브가 없을 때
                    linear_dialog_put_archive_make_new_archive.visibility=View.VISIBLE
                }
            },
            errorCallback = {
                toast(R.string.network_error)
            }
        )
    }
}