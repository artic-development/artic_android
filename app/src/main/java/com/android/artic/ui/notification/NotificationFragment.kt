package com.android.artic.ui.notification

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.android.artic.ui.base.BaseFragment
import com.android.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_notification.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class NotificationFragment : BaseFragment(R.layout.fragment_notification) {
    private val repository: ArticRepository by inject()
    private lateinit var adapterNewNotification: NotificationAdapter
    private lateinit var adapterOldNotification: NotificationAdapter

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

            repository.getNotification(
                successCallback = {
                    // 새로운 알림
                    it.filter { !it.isRead }.let {
                        if (it.isNotEmpty()) {
                            container_notification_new.visibility = View.VISIBLE
                            relative_frag_notification_no_alert.visibility = View.GONE
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
                        adapterOldNotification.data = it
                        adapterOldNotification.notifyDataSetChanged()
                    }

                },
                errorCallback = {
                    toast(R.string.network_error)
                }
            )
        }
    }

    override fun onResumeFragment() {
        super.onResumeFragment()
        repository.readNotification(
            successCallback = {
                logger.log("read notification")
                repository.getNotification(
                    successCallback = {
                        // 새로운 알림
                        it.filter { !it.isRead }.let {
                            if (it.isNotEmpty()) {
                                container_notification_new.visibility = View.VISIBLE
                                relative_frag_notification_no_alert.visibility = View.GONE
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
                            adapterOldNotification.data = it
                            adapterOldNotification.notifyDataSetChanged()
                        }

                    },
                    errorCallback = {
                        toast(R.string.network_error)
                    }
                )
            }
        )
    }
}
