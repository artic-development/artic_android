package com.android.artic.ui.archive

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.ui.archive.data.ArchiveListData

class ArchiveListAdapter(val ctx: Context, val dataList: ArrayList<ArchiveListData>): RecyclerView.Adapter<ArchiveListAdapter.Holder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_archive_list, viewGroup, false)

        return Holder(view)
    }



    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = "린 브랜딩을 어떻게 시작해야 할까?"
        holder.scrap_num.text = "30"
        holder.article_num.text = "13"

    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById(R.id.tv_rv_item_archive_list_title) as TextView
        var scrap_num = itemView.findViewById(R.id.tv_rv_item_archive_list_scrap_num) as TextView
        var article_num = itemView.findViewById<TextView>(R.id.tv_rv_item_archive_list_article_num)
    }
}