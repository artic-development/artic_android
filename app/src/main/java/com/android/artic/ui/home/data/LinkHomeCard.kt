package com.android.artic.ui.home.data

data class LinkHomeCard(
    override val viewType: HomeCardKind = HomeCardKind.LINK,
    val link_url: String,
    val title: String,
    val curator: String,
    val img_url: String
): HomeCard