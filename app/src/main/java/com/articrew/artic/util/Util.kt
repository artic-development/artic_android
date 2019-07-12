package com.articrew.artic.util

import android.content.res.Resources

fun Int.pxToDp() : Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.dpToPx() : Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun getDomainName(url: String): String {
    TODO("일단 서버에서 데이터 보내주기로 해서 개발안함!")
}