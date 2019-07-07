package com.android.artic.ui.adapter.big_image_article

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.ui.article.ArticleActivity
import com.android.artic.ui.article_about.ArticleAboutActivity
import com.android.artic.util.dpToPx
import com.android.artic.util.setTopRound
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity

class BigImageArticleAdapter (val ctx: Context, var dataList:List<Article>):RecyclerView.Adapter<BigImageArticleAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view:View= LayoutInflater.from(ctx).inflate(R.layout.rv_item_big_image_article, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        var params =

        holder.img_url?.clipToOutline = true


       holder.img_url?.let{
           Glide.with(ctx)
               .load(dataList[position].title_img_url)
               .into(it)
       }
        holder.artic_pick_url?.text=dataList[position].url
        holder.title?.text=dataList[position].title
        holder.container?.setOnClickListener {
            ctx.startActivity<ArticleAboutActivity>("articleId" to dataList[position].id)
        }
    }

    override fun getItemCount(): Int =dataList.size

    inner class Holder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val container=itemView.findViewById<View?>(R.id.rv_big_image_article_container)
        val img_url=itemView.findViewById<ImageView?>(R.id.rv_big_image_article_img)?.apply {
            setTopRound(6.dpToPx().toFloat())
        }
        val title=itemView.findViewById<TextView?>(R.id.rv_big_image_article_txt)
        val artic_pick_url=itemView.findViewById<TextView?>(R.id.rv_big_image_article_url)
    }
}