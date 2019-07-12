package com.articrew.artic.ui.notification

import android.app.Activity
import android.content.Context
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.data.notification.*
import com.articrew.artic.ui.article.ArticleActivity
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.notification.article_fragment.RawArticleListFragment
import com.articrew.artic.util.*
import com.bumptech.glide.Glide
import khronos.toString
import org.jetbrains.anko.startActivity
import java.util.*

class NotificationAdapter(
    private val context: FragmentActivity,
    var data: List<AppNotification>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(NotificationType.values()[viewType]) {
            NotificationType.ADD_ARTICLE, NotificationType.REMIND_ARCHIVE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.rv_item_add_article_notification, parent, false)
                AddArticleNotificationHolder(view)
            }
            NotificationType.RECOMMEND_ARCHIVE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.rv_item_recommend_archive_noti, parent, false)
                RecommendArchiveHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when(NotificationType.values()[viewType]) {
            NotificationType.ADD_ARTICLE -> {
                val cur = data[position] as AddArticleNotification
                (holder as AddArticleNotificationHolder).run {
                    img?.apply {
                        Glide.with(context)
                            .load(cur.img_url)
                            .apply(defaultHolderOptions)
                            .into(this)
                    }
                    txt_date?.text = Date().howMuchPreviousFrom(cur.date)

                    // TODO 얘 어떻게 여러가지 형태의 bold, normal text 혼용할 거야?
                    txt_title?.text = Html.fromHtml("<b>'${cur.archive_title}'</b>에 <b>새로운 아티클</b>이 추가되었습니다.")

                    container?.setOnClickListener {
                        // TODO cur.archiveId 를 이용해 아카이브로 이동!
                        context.startActivity<ArticleActivity>(
                            "archiveId" to cur.archive_id,
                            "archiveTitle" to cur.archive_title
                        )
                    }
                }
            }
            NotificationType.REMIND_ARCHIVE -> {
                val cur = data[position] as RemindArticleNotification
                (holder as AddArticleNotificationHolder).run {
                    img?.apply {
                        Glide.with(context)
                            .load(cur.img_url)
                            .apply(defaultHolderOptions)
                            .into(this)
                    }
                    txt_date?.text = Date().howMuchPreviousFrom(cur.date)

                    // TODO 얘 어떻게 여러가지 형태의 bold, normal text 혼용할 거야?
                    txt_title?.text = Html.fromHtml("회원님이 담은 <b>'${cur.articleName}'</b> 외 <b>${cur.num_article-1}개의 아티클</b>을 아직 읽지 않으셨습니다.")

                    container?.setOnClickListener {
                        context.supportFragmentManager.beginTransaction().apply {
                            replace(android.R.id.content, RawArticleListFragment("읽지 않은 아티클", cur.articleList))
                            addToBackStack(null)
                        }.commit()
                    }
                }
            }
            NotificationType.RECOMMEND_ARCHIVE -> {
                val cur = data[position] as RecommendArticleNotification
                (holder as RecommendArchiveHolder).run {
                    img?.apply {
                        Glide.with(context)
                            .load(cur.img_url)
                            .apply(defaultHolderOptions)
                            .into(this)
                    }
                    Log.d("noti", "${Date().toString("yyyy-MM-dd hh:mm:ss")} ${cur.date.toString("yyyy-MM-dd hh:mm:ss")}")
                    txt_date?.text = Date().howMuchPreviousFrom(cur.date)

                    txt_title?.text = Html.fromHtml("회원님이 좋아하실 만한 <b>아티클</b>을 추천해 드려요!")

                    for (i in 0..2) {
                        if (i >= cur.articleImgUrls.size) break
                        img_article_list[i]?.apply {
                            Glide.with(context)
                                .load(cur.articleImgUrls[i])
                                .apply(defaultHolderOptions)
                                .into(this)
                        }
                    }
                    container?.setOnClickListener {
                        context.supportFragmentManager.beginTransaction().apply {
                            replace(android.R.id.content, RawArticleListFragment("당신을 위한 아티클", cur.articleList))
                            addToBackStack(null)
                        }.commit()
                    }
                }
            }
        }
    }

    // 여러가지 아이템 요소를 그려주기 위해서 viewType 설정
    override fun getItemViewType(position: Int): Int = data[position].viewType.ordinal

    inner class AddArticleNotificationHolder(view: View): RecyclerView.ViewHolder(view) { //NotificationType->ADD_ARTICLE, REMIND_ARCHIVE
        val container = view.findViewById<View?>(R.id.container_rv_item_article_notification)
        val img = view.findViewById<ImageView?>(R.id.img_rv_item_article_noti)?.apply {
            clipToOutline = true
        }
        val txt_title = view.findViewById<TextView?>(R.id.txt_rv_item_article_noti_title)
        val txt_date = view.findViewById<TextView?>(R.id.txt_rv_item_article_noti_date)
    }

    inner class RecommendArchiveHolder(view: View): RecyclerView.ViewHolder(view) { //NotificationType->RECOMMEND_ARCHIVE
        val container = view.findViewById<View?>(R.id.container_rv_item_recommend_noti)
        val img = view.findViewById<ImageView?>(R.id.img_rv_item_recommend_noti)?.apply {
            clipToOutline = true
        }
        val txt_title = view.findViewById<TextView?>(R.id.txt_rv_item_recommend_noti_title)
        val txt_date = view.findViewById<TextView?>(R.id.txt_rv_item_recommend_noti_date)
        val img_article_list = listOf(
            view.findViewById<ImageView?>(R.id.img_rv_item_recommend_noti_article_left)?.apply {
                setLeftRound(6.dpToPx().toFloat())
            },
            view.findViewById<ImageView?>(R.id.img_rv_item_recommend_noti_article_center),
            view.findViewById<ImageView?>(R.id.img_rv_item_recommend_noti_article_right)?.apply {
                setRightRound(6.dpToPx().toFloat())
            }
        )
    }
}