package com.articrew.artic.util

import android.app.Activity
import android.app.AlertDialog
import android.view.View

object PopupSystem {
    /**
     * 버튼을 누르면 dialog를 종료하는 popup을 만든다.
     * @param activity popup이 나타나는 곳
     * @param layoutId dialog의 layout id값
     * @param map key는 click event가 적용될 view, value는 click이벤트
     * */
    fun show(activity: Activity?, layoutId: Int, map: Map<Int, (()->Any)?>) {
        activity?.run {
            val view = layoutInflater.inflate(layoutId, null, false)
            val dialog = AlertDialog.Builder(this)
                .setView(view)
                .create()

            for (pair in map) {
                view.findViewById<View>(pair.key).setOnClickListener {
                    pair.value?.invoke()
                    dialog.dismiss()
                }
            }

            dialog.show()
        }
    }
}