package com.android.artic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.artic.ui.category.ArchiveCategory
import com.android.artic.ui.category.Category
import com.android.artic.ui.category.CategoryLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val dummyCategory =
        listOf(
            Category(
                en_name = "Design", kr_name = "디자인", children = listOf(
                    ArchiveCategory(1, "UX/UI"), ArchiveCategory(2,"BX"), ArchiveCategory(3,"그래픽"), ArchiveCategory(4,"영상/모션")
                )
            ),
            Category(
                en_name = "Plan", kr_name = "서비스 기획", children = listOf(
                    ArchiveCategory(1, "UX/UI"), ArchiveCategory(2,"BX"), ArchiveCategory(3,"그래픽"), ArchiveCategory(4,"영상/모션")
                )
            ),
            Category(
                en_name = "Management", kr_name = "매니지먼트", children = listOf(
                    ArchiveCategory(1, "UX/UI"), ArchiveCategory(2,"BX"), ArchiveCategory(3,"그래픽"), ArchiveCategory(4,"영상/모션")
                )
            )
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = CategoryLayout(this, dummyCategory).render()
        container_main.addView(view)
    }
}
