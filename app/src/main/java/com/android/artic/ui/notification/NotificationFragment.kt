package com.android.artic.ui.notification

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.data.notification.*
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseFragment
import com.android.artic.ui.adapter.deco.VerticalSpaceItemDecoration
import com.android.artic.util.dpToPx
import kotlinx.android.synthetic.main.fragment_notification.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            repository.getNewNotificationList().enqueue(
                object : Callback<List<AppNotification>> {
                    override fun onFailure(call: Call<List<AppNotification>>, t: Throwable) {
                        toast(R.string.network_error)
                    }

                    override fun onResponse(
                        call: Call<List<AppNotification>>,
                        response: Response<List<AppNotification>>
                    ) {
                        response.body()?.let {
                            if (it.isNotEmpty()) container_notification_new.visibility = View.VISIBLE
                            adapterNewNotification.data = it
                            adapterNewNotification.notifyDataSetChanged()
                        }
                    }
                }
            )

            repository.getAlreadyReadNotificationList().enqueue(
                object : Callback<List<AppNotification>> {
                    override fun onFailure(call: Call<List<AppNotification>>, t: Throwable) {
                        toast(R.string.network_error)
                    }

                    override fun onResponse(
                        call: Call<List<AppNotification>>,
                        response: Response<List<AppNotification>>
                    ) {
                        response.body()?.let {
                            if (it.isNotEmpty()) container_notification_old.visibility = View.VISIBLE
                            adapterOldNotification.data = it
                            adapterOldNotification.notifyDataSetChanged()
                        }
                    }
                }
            )
        }
    }
}
