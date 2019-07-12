package com.articrew.artic.repository.remote.response

data class NotificationResponse (
    val articles: List<ArticleResponse>,
    val isRead: Boolean,
    val notification_type: String,
    val notification_date: String,
    val notification_id: String
)
