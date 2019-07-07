package com.android.artic.ui.mypage.mypage


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.android.artic.R
import com.android.artic.data.Archive
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.new_archive.MakeNewArchiveActivity
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.view.*
import kotlinx.android.synthetic.main.fragment_my_page_me.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 *
 */
class MyPageMeFragment : Fragment() {
    private val repository: ArticRepository by inject()
    private lateinit var adapter: MyPageMeAdapter

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_page_me, container, false)
    }

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
            repository.getMyPageMe().enqueue(
                object: Callback<List<Archive>> {
                    override fun onFailure(call: Call<List<Archive>>, t: Throwable) {
                        toast(R.string.network_error)
                    }

                    override fun onResponse(call: Call<List<Archive>>, response: Response<List<Archive>>) {
                        response.body()?.let {
                            if (it.isNotEmpty()) {
                                adapter.data = it
                                adapter.notifyDataSetChanged()
                                rv_mypage_me.visibility=View.VISIBLE
                                mypage_my_empty_view.visibility=View.GONE
                            } else {

                                rv_mypage_me.visibility=View.GONE
                                mypage_my_empty_view.visibility=View.VISIBLE
                            }
                        }
                    }
                }
            )
        }
    }
}

