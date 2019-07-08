package com.android.artic.ui.adapter.article



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.artic.R
import com.android.artic.data.Article
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.article_about.ArticleAboutActivity
import com.android.artic.ui.collect_archive.CollectArchiveDialogFragment
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject


class ArticleOverviewRecyclerViewAdapter(val ctx: FragmentActivity, var dataList:List<Article> ): RecyclerView.Adapter<ArticleOverviewRecyclerViewAdapter.Holder>() {

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
            .into(p0.img_url)
        p0.archive_url.text=dataList[p1].url
        p0.like_number.text=dataList[p1].like.toString()
        p0.link_title.text=dataList[p1].title

        // @수민 담기 버튼 리스너
        p0.btn_put.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("article_idx", dataList[p1].id)

            var putFragment = CollectArchiveDialogFragment()

            putFragment.arguments = bundle

            putFragment.show(ctx.supportFragmentManager, putFragment.tag)
        }

        // @수민) 아티클 상세보기로
        p0.relative_article_item_card.setOnClickListener {
            ctx.startActivity<ArticleAboutActivity>("articleId" to dataList[p1].id)
        }

        // @수민) 아티클의 체크 여부에 따라 토글버튼을 바꿔준다.
        p0.toggle_btn_like.isChecked = dataList[p1].isLiked!!

        // @수민) 좋아요 통신
        p0.toggle_btn_like.setOnClickListener {


            repository.postArticleLike(
                articleIdx = dataList[p1].id,
                successCallback = {
                },
                failCallback = {
                    ctx.toast(R.string.network_error)
                },
                statusCallback = {status, success, message ->
                    run {

                        if (status == 200) {

                            var like_number_int = Integer.parseInt(p0.like_number.text.toString())

                            if (message == "아티클 좋아요 성공") { // 좋아요 성공
                                p0.like_number.text = (like_number_int + 1).toString()
                                p0.toggle_btn_like.isChecked = true
                            }
                            else { // 좋아요 취소 성공
                                p0.like_number.text = (like_number_int - 1).toString()
                                p0.toggle_btn_like.isChecked = false
                            }

                            ctx.toast(message)
                        }
                        else if (status == 400) {
                            ctx.toast(message)
                        }
                        else {
                            ctx.toast("오잉")
                        }
                    }
                }

            )
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
    }
}