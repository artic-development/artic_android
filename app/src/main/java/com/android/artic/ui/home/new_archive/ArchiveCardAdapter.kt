package com.android.artic.ui.home.new_archive

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.Archive
import com.android.artic.ui.archive.ArchiveActivity
import com.android.artic.ui.article.ArticleActivity
import com.bumptech.glide.Glide

class ArchiveCardAdapter(
    private val context: Context,
    var data: List<Archive>
) : RecyclerView.Adapter<ArchiveCardAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_archive_card, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.run {
            val cur = data[position]
            txt_category?.text = cur.categories?.elementAt(0)
            txt_title?.text = cur.title
            img_background?.let{
                Glide.with(context)
                    .load(cur.title_img_url)
                    .into(it)
            }
            container?.setOnClickListener {
                // TODO cur.archive_id 를 사용해서 다른 화면으로 넘어가는 코드 구현
                var intent = Intent(context, ArticleActivity::class.java)

                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = data.size

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        val container = view.findViewById<View?>(R.id.container_rv_item_archive_card)
        val txt_category = view.findViewById<TextView?>(R.id.txt_rv_item_archive_card_category)
        val txt_title = view.findViewById<TextView?>(R.id.txt_rv_item_archive_card_title)
        val img_background = view.findViewById<ImageView?>(R.id.img_rv_item_archive_card_backgound)
    }
}