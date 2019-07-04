package com.android.artic.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.data.Category
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_category.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    private val adapter: CategoryAdapter by lazy { CategoryAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        rv_category.adapter = adapter
        rv_category.layoutManager = LinearLayoutManager(this)

        repository.getCategoryList().enqueue(
            object : Callback<List<Category>> {
                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    toast(R.string.network_error)
                }

                override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                    response.body()?.let {
                        adapter.data = it
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }
}
