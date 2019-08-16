package com.articrew.artic.repository.remote.mapper

import com.articrew.artic.data.Article
import com.articrew.artic.data.notification.*
import com.articrew.artic.repository.remote.response.NotificationResponse
import com.articrew.artic.util.fromServer

object NotificationMapper {
    fun to(res: NotificationResponse): AppNotification {
        return when(res.notification_type) {
            "0" -> { // New Article
                AddArticleNotification(
//                    viewType = NotificationType.NEW_ARTICLE,
//                    date = res.notification_date.fromServer(),
//                    img_url = res.articles[0].thumnail,
//                    id = res.notification_id,
//                    isRead = res.isRead
                    isRead = res.isRead,
                    articleIdx = res.article_idx,
                    articleCount = res.article_count,
                    stringType = res.string_type,
                    viewType = NotificationType.NEW_ARTICLE,
                    id = res.notification_id,
                    date = res.notification_date.fromServer(),
                    img_url = null
                )
            }
            "1" -> {
//                RecommendArticleNotification(
//                    viewType = NotificationType.RECOMMEND_ARCHIVE,
//                    img_url = "", // 기본 이미지!
//                    date = res.notification_date.fromServer(),
//                    articleImgUrls = res
//                        .articles!!.take(3) // 최대 3개!
//                        .map { article -> article.thumnail },
//                    articleList =  res.articles.map { data -> Article(
//                        id = data.article_idx,
//                        like = data.like_cnt?:0,
//                        title_img_url = data.thumnail,
//                        title = data.article_title,
//                        url = data.link,
//                        domain_url = data.domain?:"")
//                    },
//                    id = res.notification_id,
//                    isRead = res.isRead,
//                    articleCount = null,
//                    articleIdx = null,
//                    stringType = null
//                )
                AddArticleNotification(
                    isRead = res.isRead,
                    articleIdx = res.article_idx,
                    articleCount = res.article_count,
                    stringType = res.string_type,
                    viewType = NotificationType.RECOMMEND_ARCHIVE,
                    id = res.notification_id,
                    date = res.notification_date.fromServer(),
                    img_url = null
                )
            }
            "2" -> {
                RemindArticleNotification(
                    viewType = NotificationType.UNREAD,
                    img_url = res.articles!![0].thumnail,
                    date = res.notification_date.fromServer(),
                    articleName = res.articles[0].article_title,
                    num_article = res.articles.size,
                    articleList =  res.articles.map { data -> Article(
                        id = data.article_idx,
                        like = data.like_cnt?:0,
                        title_img_url = data.thumnail,
                        title = data.article_title,
                        url = data.link,
                        domain_url = data.domain?:"")
                    },
                    id = res.notification_id,
                    isRead = res.isRead,
                    articleCount = null,
                    articleIdx = null,
                    stringType = null
                )
            }
            "3" -> {
                AddArticleNotification(
//                    viewType = NotificationType.NEW_ARTICLE,
//                    date = res.notification_date.fromServer(),
//                    img_url = res.articles[0].thumnail,
//                    id = res.notification_id,
//                    isRead = res.isRead
                    isRead = res.isRead,
                    articleIdx = res.article_idx,
                    articleCount = res.article_count,
                    stringType = res.string_type,
                    viewType = NotificationType.NEW_ARTICLE,
                    id = res.notification_id,
                    date = res.notification_date.fromServer(),
                    img_url = null
                )
            }
            else -> {
                throw IllegalArgumentException("서버에서 잘못된 정보를보냄")
            }
        }
    }
}