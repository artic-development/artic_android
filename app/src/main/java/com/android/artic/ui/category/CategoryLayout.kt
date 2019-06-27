package com.android.artic.ui.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.artic.R
import com.android.artic.ui.category.data.Category
import kotlinx.android.synthetic.main.child_category.view.*
import kotlinx.android.synthetic.main.parent_category.view.*
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.scrollView
import org.jetbrains.anko.verticalLayout

// TODO categories 가 변경될 때, 렌더링을 다시해야 하고, 바로바로 화면에 적용되어야한다.
class CategoryLayout(
    private val context: Context,
    var categories: List<Category>
) {
    private val txt_parent_category_en_name: Int = R.id.txt_parent_category_en_name
    private val txt_parent_category_kr_name: Int = R.id.txt_parent_category_kr_name
    private val btn_parent_category_children_toggle: Int = R.id.btn_parent_category_children_toggle
    private val txt_child_category_name: Int = R.id.txt_child_category_name

    private val parentCategoryCheckes: MutableList<Checkable> = mutableListOf()

    /**
     * create CategoryLayout
     * (CategoryLayout hierarchy)
     * - ScrollView
     *      - TopLayout (LinearLayout, vertical)
     *          - ParentCategory (R.layout.parent_category)
     *          - ChildrenCategoryList (LinearLayout, vertical, default : Visible GONE)
     *              - ChildCategory (R.layout.child_category)
     *              - ChildCategory
     * @see Category
     * @author greedy0110
     * */
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
                    setOnCheckedChangeListener {  _, isChecked -> onCheckedChangeParentCategory(this, childrenLayout, isChecked) }
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

    /**
     * create CategoryLayout using [Anko library](https://github.com/Kotlin/anko)
     * @exception (Not Implemented yet)
     * */
    fun renderAnko(): View {
        return context.run {
            scrollView {
                verticalLayout {
                    for (category in categories) {
                        val parentCategory = layoutInflater.inflate(R.layout.parent_category, this)
                            .apply {
                                txt_parent_category_en_name.text = category.en_name
                                txt_parent_category_kr_name.text = category.kr_name
                                container_parent_category.setOnClickListener {
                                    btn_parent_category_children_toggle.isChecked = !btn_parent_category_children_toggle.isChecked
                                }
                            }
                        val childrenCategories = verticalLayout {
                            for (child in category.children) {
                                layoutInflater.inflate(R.layout.child_category, this).apply {
                                    txt_child_category_name.text = child.name
                                }
                            }
                            visibility = View.GONE
                        }
                        parentCategory.btn_parent_category_children_toggle
                            .apply {
                                isChecked = false // 초기에는 닫힌 상태
                                parentCategoryCheckes.add(this)
                                setOnCheckedChangeListener {  _, isChecked -> onCheckedChangeParentCategory(this@apply, childrenCategories, isChecked)}
                                isEnabled = false // parentCategory 클릭으로만 변경 가능하도록
                            }
                    }
                }
            }
        }
    }

    private fun onCheckedChangeParentCategory(check: Checkable, children : View, isChecked: Boolean) {
        // 토글버튼을 누르면 토클 ui는 selector 로 변경되고
        // 체크 상태에 따라서 하부 카테고리가 보였다 안보엿다 한다.
        // TODO 단순히 View 의 visibility 를 변경하는게 아니고, 슬라이스 처럼 부드럽게 변경되도록 할 수 있을가?
        if (isChecked) {
            children.visibility = View.VISIBLE
            checkOneThenUncheckedTheOthers(check)
        } else {
            children.visibility = View.GONE
        }
    }

    private fun checkOneThenUncheckedTheOthers(check: Checkable) {
        parentCategoryCheckes
            .filter { it != check }
            .filter { it.isChecked }
            .forEach { it.isChecked = false }
    }
}