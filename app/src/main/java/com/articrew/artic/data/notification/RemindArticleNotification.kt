package com.articrew.artic.data.notification

import com.articrew.artic.data.Article
import java.util.*

data class RemindArticleNotification (
    override val viewType: NotificationType = NotificationType.UNREAD,
    override val img_url: String,
    override val date: Date,
    override val isRead: Boolean = false,
    override val id: String,
    val articleName: String,
    val num_article: Int,
    val articleList: List<Article>,
    override val articleIdx: List<Int>?,
    override val articleCount: Int?,
    override val stringType: String?
): AppNotification