package com.android.artic.data.notification

import java.util.*

data class AddArticleNotification(
    override val viewType: NotificationType,
    override val date: Date,
    override val img_url: String,
    override val isRead: Boolean = false,
    val archive_title: String,
    val archive_id: Int
): AppNotification