package com.articrew.artic.data

data class Category (
    val id: Int,
    val name: String,
    val archive_ids: List<Int>? = null // query
)