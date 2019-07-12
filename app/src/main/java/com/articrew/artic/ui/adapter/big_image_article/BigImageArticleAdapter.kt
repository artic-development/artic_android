package com.articrew.artic.ui.adapter.big_image_article

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.data.Article
import com.articrew.artic.ui.article_about.ArticleAboutActivity
import com.articrew.artic.util.defaultHolderOptions
import com.articrew.artic.util.dpToPx
import com.articrew.artic.util.setTopRound
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity

class BigImageArticleAdapter (
    val ctx: Context,
    var dataList:List<Article>,
    val widthRatio: Float = 1F // custom image hieght ratio
):RecyclerView.Adapter<BigImageArticleAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view:View= LayoutInflater.from(ctx).inflate(R.layout.rv_item_big_image_article, parent,false).apply {
            // 여기서 부모 대비 비율을 지정할 수 있다.
            layoutParams.width = (parent.width * widthRatio).toInt()
        }
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.img_url?.clipToOutline = true

        holder.img_url?.let{
              Glide.with(ctx)
                  .load(dataList[position].title_img_url)
                  .apply(defaultHolderOptions)
                  .into(it)
        }
        holder.artic_pick_url?.text=dataList[position].domain_url
        holder.title?.text=dataList[position].title
        holder.container?.setOnClickListener {
            if (dataList[position].id == -1) {
                return@setOnClickListener // 더미 데이터는 이벤트를 발생시키면 안됀다.
            }
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