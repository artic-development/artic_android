package com.android.artic.ui.category

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.Category

class CategoryAdapter(
    private val context: Context,
    var data: List<Category>
) : RecyclerView.Adapter<CategoryAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_category, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val cur = data[position]
        holder.run {
            txt_name?.text = cur.name

            container?.setOnClickListener {
                // TODO cur.id 를 이용해서 category 에 해당하는 archive 로드하는 창으로 이동
            }
        }
    }

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        val container = view.findViewById<View?>(R.id.container_rv_item_category)
        val txt_name = view.findViewById<TextView?>(R.id.txt_rv_item_category_name)
    }
}