package com.articrew.artic.ui.notification

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.articrew.artic.ui.base.BaseFragment
import com.articrew.artic.util.dpToPx
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_notification.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject

class NotificationFragment : BaseFragment(R.layout.fragment_notification) {
    private val repository: ArticRepository by inject()
    private lateinit var adapterNewNotification: NotificationAdapter
    private lateinit var adapterOldNotification: NotificationAdapter

    val numNewNotice = BehaviorSubject.createDefault(0)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            adapterNewNotification = NotificationAdapter(this, listOf())
            adapterOldNotification = NotificationAdapter(this, listOf())

            rv_notification_new.adapter = adapterNewNotification
            rv_notification_new.layoutManager = LinearLayoutManager(this)
            rv_notification_new.addItemDecoration(
                VerticalSpaceItemDecoration(
                    this,
                    25.dpToPx()
                )
            )

            rv_notification_old.adapter = adapterOldNotification
            rv_notification_old.layoutManager = LinearLayoutManager(this)
            rv_notification_old.addItemDecoration(
                VerticalSpaceItemDecoration(
                    this,
                    25.dpToPx()
                )
            )

            // 리스트가 비어있으면 보이지 않아야한다.
            container_notification_new.visibility = View.GONE
            container_notification_old.visibility = View.GONE

            getNotification()
        }
    }

    // 이 탭을 들렸다가 나갈때 알림을 갱신한다.
    // 사용자가 알림 확인 후 다시 들아오면 읽은 알람으로 변경되있도
    override fun onPauseFragment() {
        super.onPauseFragment()
        repository.readNotification()
            .subscribe {
                logger.log("read notification")
                getNotification()
            }.apply { addDisposable(this) }
    }

    private fun getNotification() {
        repository.getNotification()
            .subscribe(
                {
                    if (it.isEmpty()) {
                        // 알람이 없으면 엠티뷰를 보여준다.
                        relative_frag_notification_no_alert.visibility = View.VISIBLE
                    }

                    // 새로운 알림
                    it.filter { !it.isRead }.let {
                        numNewNotice.onNext(it.size)
                        if (it.isNotEmpty()) {
                            container_notification_new.visibility = View.VISIBLE
                            relative_frag_notification_no_alert.visibility = View.GONE
                        }
                        else {
                            container_notification_new.visibility = View.GONE
                        }
                        adapterNewNotification.data = it
                        adapterNewNotification.notifyDataSetChanged()
                    }

                    // 읽은 알람
                    it.filter { it.isRead }.let {
                        if (it.isNotEmpty()) {
                            container_notification_old.visibility = View.VISIBLE
                            relative_frag_notification_no_alert.visibility = View.GONE
                        }
                        else {
                            container_notification_old.visibility = View.GONE
                        }
                        adapterOldNotification.data = it
                        adapterOldNotification.notifyDataSetChanged()
                    }
                },
                {
                    logger.error("notification fragment get notification error")
                    toast(R.string.network_error)
                }
            ).apply { addDisposable(this) }
    }

    override fun requestTopScroll() {
        super.requestTopScroll()
        scrollview_fragment_notification.isSmoothScrollingEnabled = true
        scrollview_fragment_notification.smoothScrollTo(0,0)
    }
}
