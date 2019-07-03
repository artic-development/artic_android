package com.android.artic.data

data class Archive (
    val id: Int,
    val category_ids: List<Int>,
    val title: String,
    val title_img_url: String,
    val num_article: Int,
    val article_ids: List<Int>? = null, // query
    val isScarped: Boolean? = null // user data
)