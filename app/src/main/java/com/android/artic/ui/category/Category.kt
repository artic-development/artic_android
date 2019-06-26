package com.android.artic.ui.category

data class Category (
    val en_name: String,
    val kr_name: String,
    val children: List<ArchiveCategory>
)

data class ArchiveCategory(
    val id: Int,
    val name: String
)