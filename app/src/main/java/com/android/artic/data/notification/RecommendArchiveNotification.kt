package com.android.artic.data.notification

import java.util.*

data class RecommendArchiveNotification (
    override val viewType: NotificationType,
    override val img_url: String,
    override val date: Date,
    val archiveId: Int,
    val articleImgUrls: List<String>
): AppNotification