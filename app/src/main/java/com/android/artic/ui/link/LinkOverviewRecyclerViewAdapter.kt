package com.android.artic.ui.link



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.link.LinkOverviewData
import com.bumptech.glide.Glide

class LinkOverviewRecyclerViewAdapter(val ctx: Context,val dataList:ArrayList<LinkOverviewData> ): RecyclerView.Adapter<LinkOverviewRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view:View= LayoutInflater.from(ctx).inflate(R.layout.rv_item_link_list,p0,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =dataList.size

    override fun onBindViewHolder(p0: Holder, p1: Int) {

        p0.img_url.clipToOutline = true // xml 파일에서 background를 라운드 처리하고, 이 코드도 추가해야 정상적으로 적용이 된다.

        Glide.with(ctx)
            .load(dataList[p1].img)
            .into(p0.img_url)
        p0.archive_url.text=dataList[p1].archive_url
        p0.like_number.text=dataList[p1].like_number.toString()
        p0.link_title.text=dataList[p1].link_title

    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val archive_url=itemView.findViewById(R.id.rv_link_list_url) as TextView
        val link_title=itemView.findViewById(R.id.rv_link_list_title) as TextView
        val like_number=itemView.findViewById(R.id.rv_link_list_like_num) as TextView
        val img_url =itemView.findViewById(R.id.rv_link_list_img) as ImageView
    }
}