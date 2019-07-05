package com.android.artic.ui.adapter.archive

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.Archive
import com.android.artic.ui.HorizontalSpaceItemDecoration
import com.android.artic.ui.article.ArticleActivity
import com.android.artic.util.dpToPx

class ArchiveListAdapter(val ctx: Context, var dataList: List<Archive>): RecyclerView.Adapter<ArchiveListAdapter.Holder>(){

    lateinit var detailNewArchiveCategoryAdapter: ArchiveCategoryAdapter

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_archive_list, viewGroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tv_title.text = dataList[position].title
        holder.ibtn_scrap.setOnClickListener{
            // 스크랩 버튼 서버 통신

        }
        holder.tv_article_num.text = dataList[position].num_article.toString()

        // 카테고리 리스트 어댑터 설정
//        detailNewArchiveCategoryAdapter =
//            ArchiveCategoryAdapter(
//                ctx,
//                dataList[position].categories!!
//            )
        // 위 코드로 바꿔야 함
        var categoryList = listOf("Management", "UI/UX")

        detailNewArchiveCategoryAdapter =
            ArchiveCategoryAdapter(
                ctx,
                categoryList
            )

        holder.rv_category_list.adapter = detailNewArchiveCategoryAdapter
        holder.rv_category_list.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)

        // 카레고리 리스트 간격 설정
        var spacesItemDecoration = HorizontalSpaceItemDecoration(ctx, 6.dpToPx(), 28.dpToPx())
        holder.rv_category_list.addItemDecoration(spacesItemDecoration)

        holder.relative_archive_list_card.setOnClickListener {
            var intent = Intent(ctx, ArticleActivity::class.java)

            ctx.startActivity(intent)
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tv_title = itemView.findViewById<TextView>(R.id.tv_rv_item_archive_list_title)
        var ibtn_scrap = itemView.findViewById<ToggleButton>(R.id.ibtn_rv_item_archive_list_scrap)
        var tv_article_num = itemView.findViewById<TextView>(R.id.tv_rv_item_archive_list_article_num)
        var rv_category_list = itemView.findViewById<RecyclerView>(R.id.rv_rv_item_archive_list_category_list)
        var relative_archive_list_card = itemView.findViewById<RelativeLayout>(R.id.relative_rv_item_archive_list)
    }
}