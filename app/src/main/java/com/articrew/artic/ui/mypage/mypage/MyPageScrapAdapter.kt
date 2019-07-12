package com.articrew.artic.ui.mypage.mypage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.data.Archive
import com.articrew.artic.util.defaultHolderOptions
import com.articrew.artic.ui.article.ArticleActivity
import com.bumptech.glide.Glide

class MyPageScrapAdapter(val ctx: Context, var data: List<Archive>) : RecyclerView.Adapter <MyPageScrapAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View= LayoutInflater.from(ctx).inflate(R.layout.rv_item_mypage_scrap,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.run{

            val cur= data[position]
            img_url?.let{
            Glide.with(ctx)
                .load(cur.title_img_url)
                .apply(defaultHolderOptions)
                .into(it)
        }
        scrap_title?.text=cur.title
//        scrap_category?.text=cur.categories?.elementAt(0)
        scrap_category?.text = cur.category_title

            holder.container?.setOnClickListener {
            var intent = Intent(ctx, ArticleActivity::class.java)

                intent.putExtra("archiveId", data[position].id)
                intent.putExtra("archiveScraped", true)
                intent.putExtra("archiveTitle", data[position].title)
                intent.putExtra("categoryTitle", data[position].category_title)

                ctx.startActivity(intent)
            }
        }
    }


    inner class Holder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val container=itemView.findViewById<View?>(R.id.rv_item_mypage_scrap_container)
        val img_url=itemView.findViewById<ImageView?>(R.id.rv_item_mypage_scrap_img)?.apply {
            clipToOutline = true
        }
        val scrap_category=itemView.findViewById<TextView?>(R.id.rv_item_mypage_scrap_title)
        val scrap_title=itemView.findViewById<TextView?>(R.id.rv_item_mypage_scrap_txt)
    }
}