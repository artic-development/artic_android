package com.articrew.artic.ui.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.ui.search.data.RecommendWordData
import com.articrew.artic.ui.search_result.SearchResultActivity
import org.jetbrains.anko.startActivity

class RecommendWordAdapter(val ctx: Context, var dataList: List<RecommendWordData>): RecyclerView.Adapter<RecommendWordAdapter.Holder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_search_recommend_card, viewGroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.recommend_word.text = dataList[position].word

        // @수민) 추천 검색어를 누르면 검색 결과 화면으로 이동
        holder.card.setOnClickListener {
            ctx.startActivity<SearchResultActivity>("searchKeyword" to dataList[position].word)
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var recommend_word = itemView.findViewById<TextView>(R.id.tv_search_card_title)
        var card = itemView.findViewById<LinearLayout>(R.id.linear_rv_search_recommend_card)
    }
}