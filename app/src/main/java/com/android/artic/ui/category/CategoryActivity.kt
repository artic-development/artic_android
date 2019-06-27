package com.android.artic.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.category.data.Category
import kotlinx.android.synthetic.main.activity_category.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity() {
    private val repository: ArticRepository by inject()
    private val categoryLayout by lazy { CategoryLayout(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        frame_category.addView(categoryLayout.render())

        repository.getCategoryList().enqueue(
            object : Callback<List<Category>> {
                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    toast("카테고리 로드 실")
                }

                override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                    response.body()?.let {
                        categoryLayout.categories = it
                        frame_category.removeAllViews()
                        frame_category.addView(categoryLayout.render())
                    }
                }
            }
        )
    }
}
