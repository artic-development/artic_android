package com.articrew.artic.ui.home.category_archive

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.data.Archive
import com.articrew.artic.ui.article.ArticleActivity
import com.articrew.artic.util.defaultHolderOptions
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity

class CategoryArchiveCardAdapter(
    private val context: Context,
    var data: List<Archive>,
    private val categoryName: String
) : RecyclerView.Adapter<CategoryArchiveCardAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_category_archive_card, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        Log.v("soomsoom", "position = $position || scrap = ${data[position].scrap}")

        holder.run {
            val cur = data[position]
            img_background?.let{
                Glide.with(context)
                    .load(cur.title_img_url)
                    .apply(defaultHolderOptions)
                    .into(it)
            }
            txt_title?.text = cur.title
            txt_articenum?.text = String.format("아티클 %d개", cur.num_article) // TODO 아티클 이라는 단어를 strings.xml에서 관리할 수 없나?
            // @수민) 카드 라운드 처리
            img_background?.clipToOutline = true

            // @수민) 카드를 누르면 아카이브 상세 보기 (링크 목록) 으로 이동
            container?.setOnClickListener {
                context.startActivity<ArticleActivity>(
                    "archiveTitle" to cur.title,
                    "categoryTitle" to categoryName,
                    "archiveId" to cur.id,
                    "archiveScraped" to cur.scrap
                )
            }
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