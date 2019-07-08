package com.android.artic.ui.category

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.data.Category
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseFragment
import com.android.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.android.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment : BaseFragment(R.layout.fragment_category) {
    private val repository: ArticRepository by inject()
    private lateinit var adapter: CategoryAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)

        activity?.run {
            adapter = CategoryAdapter(this, listOf())
            rv_category.adapter = adapter
            rv_category.layoutManager = LinearLayoutManager(this)
            rv_category.addItemDecoration(VerticalSpaceItemDecoration(this, 26.dpToPx()))

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
