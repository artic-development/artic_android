package com.android.artic.ui.article_about

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
import com.android.artic.ui.article_webview.ArticleWebViewActivity
import com.android.artic.util.setTopRound
import com.bumptech.glide.Glide

class ArticleAdapter(
    private val context: Context,
    var data: List<Article>
): RecyclerView.Adapter<ArticleAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_article_about_article, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.run {
            val cur = data[position]
            img?.let {
                Glide.with(context)
                    .load(cur.title_img_url)
                    .into(it)
            }
            txt_title?.text = cur.title
            txt_url?.text = cur.url // TODO url의 뒷부분만 보여줘야함. 어캐할래? .com .co.kr ... 등등 파서를 만들어야할듯?
            container?.setOnClickListener {
                // TODO 아티클 읽어야함
                var intent = Intent(context, ArticleWebViewActivity::class.java)

                context.startActivity(intent)
            }
        }
    }

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        val container = view.findViewById<View?>(R.id.container_rv_item_article_about_article)
        val img = view.findViewById<ImageView?>(R.id.img_rv_item_article_about_article)?.apply {
            // TODO 일단 20F로 그냥 임의의 숫자를 줫다. DP인지 PX인지 몰라서; 디자인상은 6DP임! 수정필요
            setTopRound(20F)
        }
        val txt_title = view.findViewById<TextView?>(R.id.txt_rv_item_article_about_article_title)
        val txt_url = view.findViewById<TextView?>(R.id.txt_rv_item_article_about_article_url)
    }
}