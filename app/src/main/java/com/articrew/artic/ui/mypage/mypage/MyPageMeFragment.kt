package com.articrew.artic.ui.mypage.mypage


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager

import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseFragment
import com.articrew.artic.ui.new_archive.MakeNewArchiveActivity
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_my_page_me.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject


/**
 * A simple [Fragment] subclass.
 *
 */
class MyPageMeFragment : BaseFragment(R.layout.fragment_my_page_me) {
    private val repository: ArticRepository by inject()
    private lateinit var adapter: MyPageMeAdapter
    val requireAddArchiveButton = BehaviorSubject.createDefault(false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            initialView()

            adapter = MyPageMeAdapter(this, listOf())
            rv_mypage_me.adapter = adapter
            rv_mypage_me.layoutManager = GridLayoutManager(this,2)
        }

        mypage_my_empty_view.setOnClickListener {
            ctx.startActivity(Intent(ctx, MakeNewArchiveActivity::class.java))
        }
    }

    private fun initialView() {
        rv_mypage_me.visibility=View.GONE
        mypage_my_empty_view.visibility=View.VISIBLE
    }

    override fun onResume() {
        super.onResume()

        activity?.run {
            repository.getMyPageMe()
                .subscribe(
                    {
                        if(it.isNotEmpty()) {
                            requireAddArchiveButton.onNext(true)

                            adapter.data = it
                            adapter.notifyDataSetChanged()
                            rv_mypage_me.visibility = View.VISIBLE
                            mypage_my_empty_view.visibility = View.GONE
                        }else{ // 데이터가 없으면 커다란 아카이브 추가 버튼이 있을것
                            requireAddArchiveButton.onNext(false)

                            rv_mypage_me.visibility=View.GONE
                            mypage_my_empty_view.visibility=View.VISIBLE
                        }
                    },
                    {
                        logger.error("my page me fragment get my page me error $it")
                        toast(R.string.network_error)
                    }
                ).apply { addDisposable(this) }
        }
    }
}

