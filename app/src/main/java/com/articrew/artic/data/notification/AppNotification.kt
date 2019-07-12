package com.articrew.artic.data.notification

import java.util.*

interface AppNotification {
    val viewType: NotificationType
    val img_url: String
    val date: Date // 서버에서 String 으로 받아올텐데 사용을 Date로 하지않을까
    val isRead: Boolean
    val id: String
}