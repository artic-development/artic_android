package com.android.artic.ui.home.category_archive

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.Archive
import com.bumptech.glide.Glide

class CategoryArchiveCardAdapter(
    private val context: Context,
    var data: List<Archive>
) : RecyclerView.Adapter<CategoryArchiveCardAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_category_archive_card, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.run {
            val cur = data[position]
            img_background?.let{
                Glide.with(context)
                    .load(cur.title_img_url)
                    .into(it)
            }
            txt_title?.text = cur.title
            txt_articenum?.text = String.format("아티클 %d개", cur.num_article) // TODO 아티클 이라는 단어를 strings.xml에서 관리할 수 없나?
        }
    }

    override fun getItemCount() = data.size

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val container = view.findViewById<View?>(R.id.container_rv_item_category_archive_card)
        val txt_title = view.findViewById<TextView?>(R.id.txt_rv_item_category_archive_card_title)
        val txt_articenum = view.findViewById<TextView?>(R.id.txt_rv_item_category_archive_card_articlenum)
        val img_background = view.findViewById<ImageView?>(R.id.img_rv_item_category_archive_card_background)
    }
}