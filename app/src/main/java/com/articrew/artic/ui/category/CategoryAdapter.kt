package com.articrew.artic.ui.category

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.data.Archive
import com.articrew.artic.data.Category
import com.articrew.artic.ui.archive.ArchiveActivity

class CategoryAdapter(
    private val context: Context,
    var data: List<Category>,
    private val heightRaito: Float = 1f
) : RecyclerView.Adapter<CategoryAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_category, parent, false).apply {
            // 여기서 부모 대비 비율을 지정할 수 있다.
          //  layoutParams.height = (parent.height * heightRaito).toInt()
        }
        return Holder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val cur = data[position]
        holder.run {
            txt_name?.text = cur.name

            container?.setOnClickListener {
                // TODO cur.id 를 이용해서 category 에 해당하는 archive 로드하는 창으로 이동
                var intent = Intent(context, ArchiveActivity::class.java)

                intent.putExtra("category_idx", cur.id)
                intent.putExtra("category_name", cur.name)

                context.startActivity(intent)
            }
        }
    }

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        val container = view.findViewById<View?>(R.id.container_rv_item_category)
        val txt_name = view.findViewById<TextView?>(R.id.txt_rv_item_category_name)
    }
}