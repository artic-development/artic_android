package com.articrew.artic.data

data class Article(
    val id: Int,
    val title: String,
    val url: String,
    val title_img_url: String,
    val like: Int,
    val domain_url: String = "artic",
    val isLiked: Boolean? = null, // user data
    val archive_id: Int? = null,
    val archive_title: String? = null
//    val isLiked : Boolean
) {
    companion object {
        fun createDummy() = Article(
            id = -1,
            title = "",
            url = "",
            title_img_url = "",
            like = 0
        )
    }
}