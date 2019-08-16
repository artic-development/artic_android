package com.articrew.artic.repository.remote.response

data class NotificationResponse (
    val isRead: Boolean,
    val articles: List<ArticleResponse>?,
    val article_idx : List<Int>, // @수민) 추가
    val article_count : Int, // @수민) 추가
    val string_type : String?, // @수민) 추가
    val notification_type: String,
    val notification_id: String,
    val notification_date: String
)
