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
                    viewType = NotificationType.ADD_ARTICLE,
                    date = res.notification_date.fromServer(),
                    img_url = res.articles[0].thumnail,
                    archive_title = res.articles[0].archive_title?:"",
                    archive_id = res.articles[0].archive_idx?:-1,
                    id = res.notification_id,
                    isRead = res.isRead
                )
            }
            "1" -> {
                RecommendArticleNotification(
                    viewType = NotificationType.RECOMMEND_ARCHIVE,
                    img_url = "", // 기본 이미지!
                    date = res.notification_date.fromServer(),
                    articleImgUrls = res
                        .articles.take(3) // 최대 3개!
                        .map { article -> article.thumnail },
                    articleList =  res.articles.map { data -> Article(
                        id = data.article_idx,
                        like = data.like_cnt?:0,
                        title_img_url = data.thumnail,
                        title = data.article_title,
                        url = data.link,
                        domain_url = data.domain?:"")
                    },
                    id = res.notification_id,
                    isRead = res.isRead
                )
            }
            "2" -> {
                RemindArticleNotification(
                    viewType = NotificationType.REMIND_ARCHIVE,
                    img_url = res.articles[0].thumnail,
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
                    isRead = res.isRead
                )
            }
            else -> {
                throw IllegalArgumentException("서버에서 잘못된 정보를보냄")
            }
        }
    }
}