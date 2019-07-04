package com.android.artic.ui.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.ui.mypage.data.MyPageMeData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_item_mypage_me.view.*

class MyPageMeAdapter(val ctx: Context, var dataList:List<MyPageMeData>): RecyclerView.Adapter<MyPageMeAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View=LayoutInflater.from(ctx).inflate(R.layout.rv_item_mypage_me,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
         holder.img_url?.let{
             Glide.with(ctx)
                 .load(dataList[position].directory_img)
                 .into(it)

         }
        holder.directory_name?.text=dataList[position].directory_name
        holder.container?.setOnClickListener {

        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val container=itemView.findViewById<View?>(R.id.rv_item_mypage_me_container)
        val img_url=itemView.findViewById<ImageView?>(R.id.rv_item_mypage_me_img)
        val directory_name=itemView.findViewById<TextView?>(R.id.rv_item_mypage_me_directory_name)
    }
}