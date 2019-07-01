package com.android.artic.ui.home.artic_pick

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.ui.home.artic_pick.data.ArticPickData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_item_artic_pick.view.*

class ArticPickAdapter (val ctx: Context,   var dataList:List<ArticPickData>):RecyclerView.Adapter<ArticPickAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view:View= LayoutInflater.from(ctx).inflate(R.layout.rv_item_artic_pick, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
       holder.img_url?.let{
           Glide.with(ctx)
               .load(dataList[position].img_url)
               .into(it)
       }
        holder.artic_pick_url?.text=dataList[position].artic_pick_url
        holder.title?.text=dataList[position].title
        holder.container?.setOnClickListener {

        }

    }



    override fun getItemCount(): Int =dataList.size




    inner class Holder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val container=itemView.findViewById<View?>(R.id.rv_artic_pick_container)
        val img_url=itemView.findViewById<ImageView?>(R.id.rv_artic_pick_img)
        val title=itemView.findViewById<TextView?>(R.id.rv_artic_pick_txt)
        val artic_pick_url=itemView.findViewById<TextView?>(R.id.rv_artic_pick_url)
    }
}