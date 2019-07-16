package com.articrew.artic.ui.adapter.archive
import androidx.core.content.ContextCompat

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.data.Archive
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.adapter.deco.HorizontalSpaceItemDecoration
import com.articrew.artic.ui.article.ArticleActivity
import com.articrew.artic.util.dpToPx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class ArchiveListAdapter(val ctx: FragmentActivity, var dataList: List<Archive>): RecyclerView.Adapter<ArchiveListAdapter.Holder>(){

    private val repository : ArticRepository by ctx.inject()
    lateinit var detailNewArchiveCategoryAdapter: ArchiveCategoryAdapter


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_archive_list, viewGroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tv_title.text = dataList[position].title

        // 스크랩 버튼 select 설정
        (dataList[position].scrap)?.let {
            holder.ibtn_scrap.isChecked = it

        }
        Log.v("soominsoomin", dataList[position].toString())

        holder.ibtn_scrap.setOnClickListener{
            // 스크랩 버튼 서버 통신
            // TODO 구독 관리
            repository.postArchiveScrap(dataList[position].id)
                .subscribe {
                    ctx.toast(it)
                    dataList[position].scrap?.let {
                        dataList[position].scrap = !it
                    }
                }
        }
        holder.tv_article_num.text = dataList[position].num_article.toString()

        // 카테고리 리스트 어댑터 설정
        detailNewArchiveCategoryAdapter =
            ArchiveCategoryAdapter(
                ctx,
                dataList[position].categories!!
            )

        holder.rv_category_list.adapter = detailNewArchiveCategoryAdapter
        holder.rv_category_list.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)

        holder.relative_archive_list_card.setOnClickListener {
            ctx.startActivity<ArticleActivity>(
                "archiveTitle" to dataList[position].title,
                "categoryTitle" to dataList[position].categories?.get(0),
                "archiveId" to dataList[position].id,
                "archiveScraped" to dataList[position].scrap
            )
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