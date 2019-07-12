package com.articrew.artic.repository.remote.response

data class ArticleResponse(
    val article_idx: Int,
    val article_title: String,
    val thumnail: String,
    val link: String,
    val domain: String? = null,
    val date: String,
    val hits: Int,
    val pick: Int? = null,
    val archive_title: String? = null,
    val archive_idx:Int? = null,
    val like: Boolean? = null,
    val like_cnt: Int? = null
)