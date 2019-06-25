package com.android.artic.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import java.lang.IllegalArgumentException

class HomeCardAdapter(
    private val context: Context,
    var data: List<HomeCard>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            HomeCardKind.ARCHIVE.ordinal -> {
                val view = LayoutInflater.from(context).inflate(R.layout.rv_item_archive_home_card, parent, false)
                ArchiveHolder(view)
            }
            HomeCardKind.LINK.ordinal -> {
                val view = LayoutInflater.from(context).inflate(R.layout.rv_item_link_home_card, parent, false)
                LinkHolder(view)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.run {
            when(this) {
                is ArchiveHolder -> {

                }
                is LinkHolder -> {

                }
                else -> IllegalArgumentException()
            }
        }
    }

    override fun getItemViewType(position: Int): Int = data[position].viewType.ordinal

    inner class ArchiveHolder(view: View): RecyclerView.ViewHolder(view) {
        val aa: Int = 1
    }

    inner class LinkHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}