package com.android.artic.ui.home.reading_history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.link.LinkOverviewData
import com.android.artic.ui.home.reading_history.data.HistoryData
import com.android.artic.ui.link.LinkOverviewRecyclerViewAdapter
import com.bumptech.glide.Glide

class ReadingHistoryAdapter(val ctx: Context, var dataList:List<HistoryData> ): RecyclerView.Adapter<ReadingHistoryAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ReadingHistoryAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_reading_history, p0, false)
        return Holder(view)
    }


    override fun getItemCount(): Int =dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.img_url?.let {
            Glide.with(ctx)
            .load(dataList[position].img_url)
                .into(it)
        }
        holder.reading_history_url?.text=dataList[position].reading_history_url
        holder.title?.text=dataList[position].title
        holder.container?.setOnClickListener {

        }
    }


        inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val container =itemView.findViewById<View?>(R.id.rv_reading_history_container)
            val reading_history_url=itemView.findViewById<TextView?>(R.id.rv_reading_history_url)
            val title=itemView.findViewById<TextView?>(R.id.rv_reading_history_title)
            val img_url=itemView.findViewById<ImageView?>(R.id.rv_reading_history_img)


        }

    }