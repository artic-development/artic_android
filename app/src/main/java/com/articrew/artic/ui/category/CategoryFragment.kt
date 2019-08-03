package com.articrew.artic.ui.category

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.adapter.deco.HorizontalSpaceItemDecoration
import com.articrew.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.articrew.artic.ui.base.BaseFragment
import com.articrew.artic.util.dpToPx
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

            repository.getCategoryList()
                .subscribe (
                    {
                        adapter.data = it
                        adapter.notifyDataSetChanged()
                    },
                    {
                        logger.error("category fragment get category list error")
                        toast(R.string.network_error)
                    }
                )
        }
    }

    override fun requestTopScroll() {
        super.requestTopScroll()
        rv_category.smoothScrollToPosition(0)
    }
}
