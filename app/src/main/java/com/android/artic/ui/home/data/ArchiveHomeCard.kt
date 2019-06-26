package com.android.artic.ui.home.data

data class ArchiveHomeCard(
    override val viewType: HomeCardKind = HomeCardKind.ARCHIVE,
    val archive_id: Int,
    val title: String,
    val desc: String,
    val img_url: String
): HomeCard