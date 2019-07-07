package com.android.artic.repository.remote.response

data class ArticleResponse(
    val article_idx: Int,
    val article_title: String,
    val thumnail: String,
    val link: String,
    val date: String,
    val hits: Int,
    val archive_title: String? = null
)