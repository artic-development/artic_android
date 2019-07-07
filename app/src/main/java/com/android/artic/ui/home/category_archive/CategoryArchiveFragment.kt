package com.android.artic.ui.home.category_archive


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
import com.android.artic.ui.archive.ArchiveActivity
import kotlinx.android.synthetic.main.fragment_category_archive.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Display Category Archive List (2x2)
 * Must create this with param!
 * @param categoryId it need for get server data
 * @param categoryName it display fragment text box
 * @author greedy0110
 */
class CategoryArchiveFragment(
    private var categoryId: Int = 0,
    private var categoryName: String = "Dummy"
) : Fragment() {

    private val repository: ArticRepository by inject()
    private lateinit var adapter: CategoryArchiveCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_archive, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            adapter = CategoryArchiveCardAdapter(this, listOf())

            txt_category_archive_name.text = categoryName
            rv_category_archive.adapter = adapter

            // 2x2 를 만들어줘야 하므로 데이터는 앞의 4개만 받아오자.
            rv_category_archive.layoutManager = GridLayoutManager(this, 2)

            // @수민) 카테고리 누르면 해당 카테고리에 해당하는 아카이브 리스트가 있는 페이지로
            linear_fragment_category_archive_category.setOnClickListener {
                var intent = Intent(this, ArchiveActivity::class.java)

                startActivity(intent)
            }

        }
    }
}
