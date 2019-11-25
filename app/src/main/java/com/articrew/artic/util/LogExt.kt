package com.articrew.artic.util

import android.util.Log

internal const val TAG = "artic"

fun String?.logDebug(tag: String = TAG) {
    Log.d(tag, this)
}

fun String?.logError(tag: String = TAG) {
    Log.e(tag, this)
}

fun String?.logWarning(tag: String = TAG) {
    Log.w(tag, this)
}