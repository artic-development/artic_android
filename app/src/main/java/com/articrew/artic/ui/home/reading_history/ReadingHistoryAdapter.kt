package com.articrew.artic.ui.home.reading_history

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.data.Article
import com.articrew.artic.ui.article_about.ArticleAboutActivity
import com.articrew.artic.util.defaultHolderOptions
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity

class ReadingHistoryAdapter(val ctx: Context, var dataList:List<Article> ): RecyclerView.Adapter<ReadingHistoryAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ReadingHistoryAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_reading_history, p0, false)
        return Holder(view)
    }


    override fun getItemCount(): Int =dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.img_url?.clipToOutline = true

        holder.img_url?.let {
            Glide.with(ctx)
            .load(dataList[position].title_img_url)
                .apply(defaultHolderOptions)
                .into(it)
        }
        holder.reading_history_url?.text=dataList[position].domain_url
        holder.title?.text=dataList[position].title
        holder.container?.setOnClickListener {
            ctx.startActivity<ArticleAboutActivity>(
                "articleId" to dataList[position].id
            )
        }
    }


        inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val container =itemView.findViewById<View?>(R.id.rv_reading_history_container)
            val reading_history_url=itemView.findViewById<TextView?>(R.id.rv_reading_history_url)
            val title=itemView.findViewById<TextView?>(R.id.rv_reading_history_title)
            val img_url=itemView.findViewById<ImageView?>(R.id.rv_reading_history_img)


        }

    }
