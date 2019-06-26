package com.android.artic.ui.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.artic.R

class CategoryLayout(
    private val context: Context,
    var categories: List<Category>
) {
    private val txt_parent_category_en_name: Int = R.id.txt_parent_category_en_name
    private val txt_parent_category_kr_name: Int = R.id.txt_parent_category_kr_name
    private val btn_parent_category_children_toggle: Int = R.id.btn_parent_category_children_toggle
    private val txt_child_category_name: Int = R.id.txt_child_category_name

    private val parentCategoryCheckes: MutableList<Checkable> = mutableListOf()

    fun render(): View {
        val topLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
        val topScrollview: ViewGroup = ScrollView(context).apply {
            addView(topLayout)
        }

        for (category in categories) {
            val parentCateoryView = LayoutInflater.from(context).inflate(R.layout.parent_category, topLayout, false) as ViewGroup
            topLayout.addView(parentCateoryView)

            val childrenLayout = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                visibility = View.GONE // 초기에는 닫힌 상태
                topLayout.addView(this)
            }

            parentCateoryView.findViewById<TextView>(txt_parent_category_en_name).text = category.en_name
            parentCateoryView.findViewById<TextView>(txt_parent_category_kr_name).text = category.kr_name
            val children_toggle = parentCateoryView.findViewById<CheckBox>(btn_parent_category_children_toggle)
                .apply {
                    isChecked = false // 초기에는 닫힌 상태
                    parentCategoryCheckes.add(this)
                    setOnCheckedChangeListener {  view, isChecked ->
                        // 토글버튼을 누르면 토클 ui는 selector 로 변경되고
                        // 체크 상태에 따라서 하부 카테고리가 보였다 안보엿다 한다.
                        // 하나가 키면, 다른 녀석들은 전부다 꺼저야 한다?!
                        if (isChecked) {
                            childrenLayout.visibility = View.VISIBLE
                            checkOneThenUncheckedTheOthers(view)
                        } else {
                            childrenLayout.visibility = View.GONE
                        }
                    }
                    isEnabled = false // parentCategory 클릭으로만 변경 가능하도록
                }

            // parentCategory 아무대를 클릭해도 토글된다.
            parentCateoryView.setOnClickListener {
                children_toggle.isChecked = !children_toggle.isChecked
            }

            for (child in category.children) {
                val childCategoryView = LayoutInflater.from(context).inflate(R.layout.child_category, childrenLayout, false)
                childrenLayout.addView(childCategoryView)
                childCategoryView.findViewById<TextView>(txt_child_category_name).text = child.name
                childCategoryView.setOnClickListener {
                    // TODO 최종적으로 이걸 누르면 별도의 archive 로 이동해야한다. child.id 를 사용!
                }
            }
        }

        return topScrollview
    }

    private fun checkOneThenUncheckedTheOthers(check: Checkable) {
        parentCategoryCheckes
            .filter { it != check }
            .filter { it.isChecked }
            .forEach { it.isChecked = false }
    }
}