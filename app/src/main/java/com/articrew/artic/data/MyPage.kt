package com.articrew.artic.data

data class MyPage(
    val name: String,
    val id: String,
    val profile_img: String?,
    val my_info: String?
) {
    companion object {
        fun createDummy(): MyPage {
            return MyPage("", "", "", "")
        }
    }
}