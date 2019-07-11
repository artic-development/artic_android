package com.android.artic.ui.category

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.adapter.deco.HorizontalSpaceItemDecoration
import com.android.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.android.artic.ui.base.BaseFragment
import com.android.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_new_archive.*
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

            var spacesItemDecoration =
                VerticalSpaceItemDecoration(this, 40.dpToPx())
            rv_category.addItemDecoration(spacesItemDecoration)

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

    override fun onResume() {
        super.onResume()


    }
}
