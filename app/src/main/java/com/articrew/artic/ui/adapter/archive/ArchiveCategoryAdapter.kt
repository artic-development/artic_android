package com.articrew.artic.ui.adapter.archive

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R

class ArchiveCategoryAdapter(val ctx: Context, var dataList: List<String>): RecyclerView.Adapter<ArchiveCategoryAdapter.Holder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_archive_category_card, viewGroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tv_category.text = dataList[position]
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tv_category = itemView.findViewById<TextView>(R.id.tv_rv_archive_category_card_category)
    }
}