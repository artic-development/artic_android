package com.articrew.artic.util

import khronos.*
import java.util.*

// TODO 영어로 바꾸면? 다른 언어로 바꾸면?!
fun Date.howMuchPreviousFrom(date: Date): String {
    var diff = time - date.time // 기준으로 부터 59분 전 date 객체

    // 분 -> 1000 * 60 = 60000
    // 시 -> 60000 * 60 = 3600000
    // 일 -> 86400000
    // 달 -> 2592000000
    // 년 -> 31104000000
    val yearDiff = diff / 31104000000
    diff -= yearDiff * 31104000000
    val monthDiff = diff / 2592000000
    diff -= monthDiff * 2592000000
    val dayDiff = diff / 86400000
    diff -= dayDiff * 86400000
    val hourDiff = diff / 3600000
    diff -= hourDiff * 3600000
    val minuteDiff = diff / 60000

    return when {
        yearDiff != 0L -> "${yearDiff}년 전"
        monthDiff != 0L -> "${monthDiff}달 전"
        dayDiff != 0L -> "${dayDiff}일 전"
        hourDiff != 0L -> "${hourDiff}시간 전"
        minuteDiff != 0L -> "${minuteDiff}분 전"
        else -> "방금"
    }
}

fun String?.fromServer(): Date {
    if (this == null) return 1.minutes.ago
    return toDate("yyyy-MM-dd'T'hh:mm:ss") + 9.hours // TODO 몽고디비 타이머가 Korean time 적용되지 않는다.
}