package com.android.artic.ui.home.new_article

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.ui.home.new_article.data.ArticleCardData
import com.bumptech.glide.Glide

class ArticleCardAdapter(val ctx: Context, var dataList: ArrayList<ArticleCardData>): RecyclerView.Adapter<ArticleCardAdapter.Holder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_home_new_article, viewGroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx).load(dataList[position].background).into(holder.img_background)
        holder.tv_title.text = dataList[position].title
        holder.tv_url.text = dataList[position].url
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var img_background = itemView.findViewById<ImageView>(R.id.img_rv_item_home_new_article_background)
        var tv_title = itemView.findViewById<TextView>(R.id.tv_rv_item_home_new_article_title)
        var tv_url = itemView.findViewById<TextView>(R.id.tv_rv_item_home_new_article_url)
    }
}