package com.android.artic.ui.new_article_link

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.ui.new_article_link.data.NewArticleLinkData
import com.bumptech.glide.Glide


class NewArticleLinkListAdapter (val ctx: Context, var dataList:List<NewArticleLinkData>) : RecyclerView.Adapter<NewArticleLinkListAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view:View=LayoutInflater.from(ctx).inflate(R.layout.rv_item_new_article_link, parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.img_url?.let {
            Glide.with(ctx)
                .load(dataList[position].img_url)
                .into(it)
        }
        holder.new_article_link_url?.text=dataList[position].new_article_link_url
        holder.title?.text=dataList[position].title
        holder.container?.setOnClickListener {

        }
    }


    inner class Holder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val container=itemView.findViewById<View?>(R.id.rv_new_article_link_container)
        val img_url=itemView.findViewById<ImageView?>(R.id.rv_new_article_link_img)
        val title=itemView.findViewById<TextView?>(R.id.rv_new_article_link_txt)
        val new_article_link_url=itemView.findViewById<TextView?>(R.id.rv_new_article_link_url)

    }
}