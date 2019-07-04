package com.android.artic.ui.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.ui.mypage.data.MyPageScrapData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_item_mypage_scrap.view.*

class MyPageScrapAdapter(val ctx: Context, var dataList: List<MyPageScrapData>) : RecyclerView.Adapter <MyPageScrapAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View= LayoutInflater.from(ctx).inflate(R.layout.rv_item_mypage_scrap,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.img_url?.let{
            Glide.with(ctx)
                .load(dataList[position].scrap_img)
                .into(it)
        }
        holder.scrap_title?.text=dataList[position].scrap_title
        holder.scrap_content?.text=dataList[position].scrap_content
        holder.container?.setOnClickListener {

        }
    }


    inner class Holder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val container=itemView.findViewById<View?>(R.id.rv_item_mypage_scrap_container)
        val img_url=itemView.findViewById<ImageView?>(R.id.rv_item_mypage_scrap_img)
        val scrap_title=itemView.findViewById<TextView?>(R.id.rv_item_mypage_scrap_title)
        val scrap_content=itemView.findViewById<TextView?>(R.id.rv_item_mypage_scrap_txt)
    }
}