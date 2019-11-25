package com.articrew.artic.ui.notification

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.articrew.artic.R
import com.articrew.artic.data.notification.AddArticleNotification
import com.articrew.artic.data.notification.AppNotification
import com.articrew.artic.data.notification.NotificationType
import com.articrew.artic.ui.article_webview.ArticleWebViewActivity
import com.articrew.artic.ui.notification.article_fragment.RawArticleListFragment
import com.articrew.artic.util.*
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity
import java.util.*

class NotificationAdapter(
    private val context: FragmentActivity,
    var data: List<AppNotification>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(NotificationType.values()[viewType]) {
            NotificationType.NEW_ARTICLE, NotificationType.UNREAD, NotificationType.ARTIC_GUIDE, NotificationType.RECOMMEND_ARCHIVE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.rv_item_add_article_notification, parent, false)
                AddArticleNotificationHolder(view)
            }

            // region 1차 스프린트 때는 안함
//            NotificationType.RECOMMEND_ARCHIVE -> { // 추천 아카이브일 때만
//                val view = LayoutInflater.from(context).inflate(R.layout.rv_item_recommend_archive_noti, parent, false)
//                RecommendArchiveHolder(view)
//            }
            // endregion
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when(NotificationType.values()[viewType]) {
            // region NotificationType.NEW_ARTICLE (새로운 아티클)
            NotificationType.NEW_ARTICLE -> {
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
                    when {
                        cur.stringType == "0" -> {
                            txt_title?.text = Html.fromHtml("아틱 크루가 <b>아티클 ${cur.articleCount}개</b>를 업데이트 했어요!")
                        }
                        cur.stringType == "1" -> {
                            txt_title?.text = Html.fromHtml("<b>새로운 아티클</b>이 업데이트 되었어요!\n지금 확인해보세요.")
                        }
                        cur.stringType == "2" -> {
                            txt_title?.text = Html.fromHtml("당신을 똑똑하게 해줄 <b>새로운 아티클</b>이\n도착했어요. 지금 확인해볼까요?")
                        }
                        cur.stringType == "3" -> {
                            txt_title?.text = Html.fromHtml("아틱 크루가 선정한 <b>새로운 아티클 ${cur.articleCount}개</b>가 업데이트 되었어요!")
                        }
                        cur.stringType == "4" -> {
                            txt_title?.text = Html.fromHtml("<b>새로운 아티클 ${cur.articleCount}개</b>를 준비했어요.\n오늘은 어떤 아티클을 만나게 될까요?")
                        }
                        cur.stringType == "5" -> {
                            txt_title?.text = Html.fromHtml("<b>새로운 아티클</b>이 도착했어요!\n지금 확인해보세요 :)")
                        }
                        else -> {
                            txt_title?.text = Html.fromHtml("아틱이 처음이신가요? 회원님을 위해 준비한\n<b>아틱 사용 설명서</b>를 확인해보세요.")
                        }
                    }

                    container?.setOnClickListener {
                        context.supportFragmentManager.beginTransaction().apply {
                            replace(android.R.id.content, RawArticleListFragment("읽지 않은 아티클", cur.articleIdx))
                            addToBackStack(null)
                        }.commit()
                    }
                }
            }
            // endregion

            // region 1차 스프린트 때는 안함
            NotificationType.RECOMMEND_ARCHIVE -> {
                // region 기존 코드
//                val cur = data[position] as RecommendArticleNotification
//                (holder as RecommendArchiveHolder).run {
//                    img?.apply {
//                        Glide.with(context)
//                            .load(cur.img_url)
//                            .apply(defaultHolderOptions)
//                            .into(this)
//                    }
//                    Log.d("noti", "${Date().toString("yyyy-MM-dd hh:mm:ss")} ${cur.date.toString("yyyy-MM-dd hh:mm:ss")}")
//                    txt_date?.text = Date().howMuchPreviousFrom(cur.date)
//
//                    txt_title?.text = Html.fromHtml("회원님이 좋아하실 만한 <b>아티클</b>을 추천해 드려요!")
//
//                    for (i in 0..2) {
//                        if (i >= cur.articleImgUrls.size) break
//                        img_article_list[i]?.apply {
//                            Glide.with(context)
//                                .load(cur.articleImgUrls[i])
//                                .apply(defaultHolderOptions)
//                                .into(this)
//                        }
//                    }
//                    container?.setOnClickListener {
//                        context.supportFragmentManager.beginTransaction().apply {
//                            replace(android.R.id.content, RawArticleListFragment("당신을 위한 아티클", cur.articleList))
//                            addToBackStack(null)
//                        }.commit()
//                    }
//                }
                // endregions
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
                    when {
                        cur.stringType == "0" -> {
                            txt_title?.text = Html.fromHtml("아틱 크루가 <b>아티클 '${cur.articleCount}'개</b>를 업데이트 했어요!")
                        }
                        cur.stringType == "1" -> {
                            txt_title?.text = Html.fromHtml("<b>새로운 아티클</b>이 업데이트 되었어요!\n지금 확인해보세요.")
                        }
                        cur.stringType == "2" -> {
                            txt_title?.text = Html.fromHtml("당신을 똑똑하게 해줄 <b>새로운 아티클</b>이\n도착했어요. 지금 확인해볼까요?")
                        }
                        cur.stringType == "3" -> {
                            txt_title?.text = Html.fromHtml("아틱 크루가 선정한 <b>새로운 아티클 '${cur.articleCount}'개</b>가 업데이트 되었어요!")
                        }
                        cur.stringType == "4" -> {
                            txt_title?.text = Html.fromHtml("<b>새로운 아티클 '${cur.articleCount}'개</b>를 준비했어요.\n오늘은 어떤 아티클을 만나게 될까요?")
                        }
                        cur.stringType == "5" -> {
                            txt_title?.text = Html.fromHtml("<b>새로운 아티클</b>이 도착했어요!\n지금 확인해보세요 :)")
                        }
                        else -> {
                            txt_title?.text = Html.fromHtml("아틱이 처음이신가요? 회원님을 위해 준비한\n<b>아틱 사용 설명서</b>를 확인해보세요.")
                        }
                    }

                    container?.setOnClickListener {
                        // TODO 새로운 아티클 알림이 왔을 떄 누르면 어디로 가는지?
//                        context.startActivity<ArticleActivity>(
//                            "archiveId" to cur.archive_id,
//                            "archiveTitle" to cur.archive_title
//                        )
                        // 아틱 사용법 눌렀을 때 바로 웹뷰 띄워지도록
                        context.startActivity<ArticleWebViewActivity>("articleId" to cur.articleIdx[0])
                    }
                }
            }
            // endregion

            // Notification.UNREAD일때도 처리를 해야하나?

            // region Notification.ARTIC_GUIDE (아틱 가이드)
            NotificationType.ARTIC_GUIDE -> {
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
                    txt_title?.text = Html.fromHtml("아틱이 처음이신가요? 회원님을 위해 준비한\n<b>아틱 사용 설명서</b>를 확인해보세요.")

                    container?.setOnClickListener {
                        // 아틱 사용법 눌렀을 때 바로 웹뷰 띄워지도록
                        context.startActivity<ArticleWebViewActivity>("articleId" to cur.articleIdx[0])
                    }
                }
            }
            // endregion
        }
    }

    // 여러가지 아이템 요소를 그려주기 위해서 viewType 설정
    override fun getItemViewType(position: Int): Int = data[position].viewType.ordinal

    inner class AddArticleNotificationHolder(view: View): RecyclerView.ViewHolder(view) { //NotificationType->NEW_ARTICLE, REMIND_ARCHIVE
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