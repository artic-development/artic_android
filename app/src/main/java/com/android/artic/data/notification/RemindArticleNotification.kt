package com.android.artic.data.notification

import java.util.*

data class RemindArticleNotification (
    override val viewType: NotificationType = NotificationType.REMIND_ARCHIVE,
    override val img_url: String,
    override val date: Date,
    override val isRead: Boolean = false,
    val articleName: String,
    val num_article: Int
): AppNotification