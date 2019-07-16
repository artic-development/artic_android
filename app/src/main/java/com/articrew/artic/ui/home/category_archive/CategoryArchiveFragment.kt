package com.articrew.artic.ui.home.category_archive


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.articrew.artic.R
import com.articrew.artic.data.Archive
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.archive.ArchiveActivity
import com.articrew.artic.ui.base.BaseFragment
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
) : BaseFragment(R.layout.fragment_category_archive) {
    private val repository: ArticRepository by inject()
    private lateinit var adapter: CategoryArchiveCardAdapter

    private lateinit var txtCategoryArchiveName: TextView
    private lateinit var rvCategoryArchive: RecyclerView
    private lateinit var containerArchive: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = super.onCreateView(inflater, container, savedInstanceState)

        // kotlin extension 으로 하면 fragment 여러개 추가할때 오류 발생
        txtCategoryArchiveName = view!!.findViewById(R.id.txt_category_archive_name)
        rvCategoryArchive = view.findViewById(R.id.rv_category_archive)
        containerArchive = view.findViewById(R.id.linear_fragment_category_archive_category)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            adapter = CategoryArchiveCardAdapter(this, listOf(), categoryName)

            txtCategoryArchiveName.text = categoryName
            rvCategoryArchive.adapter = adapter

            // 2x2 를 만들어줘야 하므로 데이터는 앞의 4개만 받아오자.
            rvCategoryArchive.layoutManager = GridLayoutManager(this, 2)

            // @수민) 카테고리 누르면 해당 카테고리에 해당하는 아카이브 리스트가 있는 페이지로
            containerArchive.setOnClickListener {
                var intent = Intent(this, ArchiveActivity::class.java)

                intent.putExtra("category_idx", categoryId)
                intent.putExtra("category_name", categoryName)

                startActivity(intent)
            }

            logger.log("category fragment $categoryId $categoryName")

            // 데이터 갱신이 onResume 마다 될 필요가 없음.
            repository.getArchiveListGivenCategory(categoryId)
                .subscribe(
                    {
                        if (it.isEmpty()) {
                            logger.log("category empty $categoryName")
                            supportFragmentManager.beginTransaction().remove(this@CategoryArchiveFragment).commitAllowingStateLoss()
                        }
                        // 최신 4개의 archive 만 가져온다!
                        it.take(4).let { cut->
                            adapter.data = cut
                            adapter.notifyDataSetChanged()
                        }
                    },
                    {
                        supportFragmentManager.beginTransaction().remove(this@CategoryArchiveFragment).commitAllowingStateLoss()
                    }
                ).apply { addDisposable(this) }
        }
    }
}
