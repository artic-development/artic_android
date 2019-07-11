package com.android.artic.data.notification

import java.util.*

data class RecommendArchiveNotification (
    override val viewType: NotificationType,
    override val img_url: String,
    override val date: Date,
    override val isRead: Boolean = false,
    val articleImgUrls: List<String>
): AppNotification