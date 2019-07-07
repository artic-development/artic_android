package com.android.artic.repository.remote.response

data class ArchiveResponse(
    val archive_idx: Int,
    val archive_img: String,
    val archive_title: String,
    val article_cnt: Int,
    val category_all: List<CategoryAll>,
    val category_idx: Int,
    val date: String,
    val user_idx: Int
)

data class CategoryAll(
    val category_title: String
)