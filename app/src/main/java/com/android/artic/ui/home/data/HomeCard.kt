package com.android.artic.ui.home.data

interface HomeCard {
    val viewType: HomeCardKind
}

enum class HomeCardKind {
    ARCHIVE, LINK
}

data class ArchiveHomeCard(
    override val viewType: HomeCardKind = HomeCardKind.ARCHIVE,
    val archive_id: Int,
    val title: String,
    val desc: String,
    val img_url: String
): HomeCard

data class LinkHomeCard(
    override val viewType: HomeCardKind = HomeCardKind.LINK,
    val link_url: String,
    val title: String,
    val curator: String,
    val img_url: String
): HomeCard