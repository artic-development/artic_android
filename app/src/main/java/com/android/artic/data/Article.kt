package com.android.artic.data

data class Article (
    val id: Int,
    val title: String,
    val url: String,
    val title_img_url: String,
    val like: Int,
    val domain_url: String = "artic",
    val isLiked: Boolean? = null // user data
//    val isLiked : Boolean
)