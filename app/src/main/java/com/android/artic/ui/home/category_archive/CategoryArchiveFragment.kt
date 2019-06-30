package com.android.artic.ui.home.category_archive


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.home.category_archive.data.CategoryArchiveCardData
import kotlinx.android.synthetic.main.fragment_category_archive.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO Activity - Fragment 사이에 데이터 전달?!
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

            repository.getCategoryArchiveList(categoryId).enqueue(
                object : Callback<List<CategoryArchiveCardData>> {
                    override fun onFailure(call: Call<List<CategoryArchiveCardData>>, t: Throwable) {
                        toast(R.string.network_error)
                    }

                    override fun onResponse(
                        call: Call<List<CategoryArchiveCardData>>,
                        response: Response<List<CategoryArchiveCardData>>
                    ) {
                        // 최신 4개의 archive 만 가져온다!
                        response.body()?.take(4)?.let {
                            adapter.data = it
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            )
        }
    }
}
