package com.android.artic.ui.category

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class CategoryFragment : BaseFragment(R.layout.fragment_category) {
    private val repository: ArticRepository by inject()
    private lateinit var adapter: CategoryAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)

        activity?.run {
            adapter = CategoryAdapter(this, listOf(), 0.1f)
            rv_category.adapter = adapter
            rv_category.layoutManager = LinearLayoutManager(this)

            repository.getCategoryList(
                successCallback = {
                    adapter.data = it
                    adapter.notifyDataSetChanged()
                },
                failCallback = {
                    toast(R.string.network_error)
                }
            )
        }
    }
}
