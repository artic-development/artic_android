package com.articrew.artic.data.notification

import com.articrew.artic.data.Article
import java.util.*

data class RecommendArticleNotification (
    override val viewType: NotificationType,
    override val img_url: String?,
    override val date: Date,
    override val isRead: Boolean = false,
    override val id: String,
    val articleImgUrls: List<String>,
    val articleList: List<Article>,
    override val articleIdx: List<Int>?,
    override val articleCount: Int?,
    override val stringType: String?
): AppNotification