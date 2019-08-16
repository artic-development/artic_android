package com.articrew.artic.data.notification

import java.util.*

interface AppNotification {
    val isRead: Boolean
    val articleIdx : List<Int>? // @수민) 추가
    val articleCount : Int? // @수민) 추가
    val stringType : String? // @수민) 추가
    val viewType: NotificationType
    val id: String
    val date: Date // 서버에서 String 으로 받아올텐데 사용을 Date로 하지않을까

    val img_url: String?
}