package com.articrew.artic.data.notification

import java.util.*

data class AddArticleNotification(
    override val isRead: Boolean,
    override val articleIdx: List<Int>,
    override val articleCount: Int,
    override val stringType: String?,
    override val viewType: NotificationType,
    override val id: String,
    override val date: Date,
    override val img_url: String?
): AppNotification