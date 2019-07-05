package com.android.artic.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.ui.search.data.RecommendWordData

class RecommendWordAdapter(val ctx: Context, var dataList: List<RecommendWordData>): RecyclerView.Adapter<RecommendWordAdapter.Holder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_search_recommend_card, viewGroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.recommend_word.text = dataList[position].word
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var recommend_word = itemView.findViewById<TextView>(R.id.tv_search_card_title)
    }
}