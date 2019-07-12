package com.articrew.artic.data

data class Archive (
    val id: Int,
    val category_ids: List<Int>? = null,
    val title: String,
    val title_img_url: String,
    val num_article: Int,
    val article_ids: List<Int>? = null, // query
    val categories: List<String>? = null, // do you wanna get this. please query
    var scrap: Boolean? = null, // user data
    val category_idx : Int? = null, //
    val category_title : String? = null
) {
    companion object {
        fun createDummy() = Archive(
            id = -1,
            title_img_url = "",
            title = "",
            num_article = 0
        )
    }
}