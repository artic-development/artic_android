package com.android.artic.ui.category.data

data class Category (
    val en_name: String,
    val kr_name: String,
    val children: List<ArchiveCategory>
)

