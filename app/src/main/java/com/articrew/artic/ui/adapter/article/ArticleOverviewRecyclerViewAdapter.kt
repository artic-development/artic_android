package com.articrew.artic.ui.adapter.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.data.Article
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.article_about.ArticleAboutActivity
import com.articrew.artic.ui.article_webview.ArticleWebViewActivity
import com.articrew.artic.ui.collect_archive.CollectArchiveDialogFragment
import com.articrew.artic.util.defaultHolderOptions
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject


class ArticleOverviewRecyclerViewAdapter(
    val ctx: FragmentActivity,
    var dataList:List<Article>,
    val isDirectWebView: Boolean
): RecyclerView.Adapter<ArticleOverviewRecyclerViewAdapter.Holder>() {

    private val repository : ArticRepository by ctx.inject()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view:View= LayoutInflater.from(ctx).inflate(R.layout.rv_item_link_list,p0,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =dataList.size

    override fun onBindViewHolder(p0: Holder, p1: Int) {

        p0.img_url.clipToOutline = true // xml 파일에서 background를 라운드 처리하고, 이 코드도 추가해야 정상적으로 적용이 된다.

        Glide.with(ctx)
            .load(dataList[p1].title_img_url)
            .apply(defaultHolderOptions)
            .into(p0.img_url)
        p0.archive_url.text=dataList[p1].domain_url
        p0.like_number.text=dataList[p1].like.toString()
        p0.link_title.text=dataList[p1].title

        // @수민 담기 버튼 리스너
        p0.txt_put.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("article_idx", dataList[p1].id)

            var putFragment = CollectArchiveDialogFragment()

            putFragment.arguments = bundle

            putFragment.show(ctx.supportFragmentManager, putFragment.tag)
        }
        p0.btn_put.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("article_idx", dataList[p1].id)

            var putFragment = CollectArchiveDialogFragment()

            putFragment.arguments = bundle

            putFragment.show(ctx.supportFragmentManager, putFragment.tag)
        }

        // @수민) 아티클 상세보기로
        p0.relative_article_item_card.setOnClickListener {
            if (isDirectWebView)
                ctx.startActivity<ArticleWebViewActivity>("articleId" to dataList[p1].id)
            else
                ctx.startActivity<ArticleAboutActivity>("articleId" to dataList[p1].id)
        }

        p0.toggle_btn_like.isChecked = false

        // @수민) 아티클의 체크 여부에 따라 토글버튼을 바꿔준다.
        dataList[p1].isLiked?.let { // dataList[p1].isLiked가 null이 아닐 때 처리
            p0.toggle_btn_like.isChecked = it
        }


        // @수민) 좋아요 통신
        p0.toggle_btn_like.setOnClickListener {
            // TODO 구독 관리
            repository.postArticleLike(dataList[p1].id)
                .subscribe {
                    val like_number_int = Integer.parseInt(p0.like_number.text.toString())
                    if (it) { // 좋아요 성공
                        p0.like_number.text = (like_number_int + 1).toString()
                    }
                    else { // 좋아요 취소 성공
                        p0.like_number.text = (like_number_int - 1).toString()
                    }
                }
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val archive_url=itemView.findViewById(R.id.rv_link_list_url) as TextView
        val link_title=itemView.findViewById(R.id.rv_link_list_title) as TextView
        val like_number=itemView.findViewById(R.id.rv_link_list_like_num) as TextView
        val img_url =itemView.findViewById(R.id.rv_link_list_img) as ImageView

        // @숨니
        var btn_put = itemView.findViewById<ImageButton>(R.id.rv_link_list_storage)
        var relative_article_item_card = itemView.findViewById<RelativeLayout>(R.id.relative_rv_item_link_list)
        var toggle_btn_like = itemView.findViewById<ToggleButton>(R.id.rv_link_list_like)
        var txt_put=itemView.findViewById<RelativeLayout>(R.id.rv_link_list_storage_txt)

    }
}