package com.articrew.artic.repository.remote.response

data class ArchiveResponse(
    val archive_idx: Int,
    val archive_img: String,
    val archive_title: String,
    val article_cnt: Int?,
    val category_all: List<CategoryAll>?,
    val category_idx: Int,
    val date: String,
    val user_idx: Int,
    val category_title: String? = null,
    val scrap : Boolean? = null
)

data class CategoryAll(
    val category_title: String
)