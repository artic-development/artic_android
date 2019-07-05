package com.android.artic.ui.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.artic.R
import com.android.artic.data.notification.*
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.ui.VerticalSpaceItemDecoration
import com.android.artic.util.dpToPx
import khronos.Dates
import khronos.days
import khronos.minutes
import kotlinx.android.synthetic.main.activity_notification.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationActivity : BaseActivity() {
    private val repository: ArticRepository by inject()
    private val adapterNewNotification: NotificationAdapter by lazy { NotificationAdapter(this, listOf()) }
    private val adapterOldNotification: NotificationAdapter by lazy { NotificationAdapter(this, listOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        rv_notification_new.adapter = adapterNewNotification
        rv_notification_new.layoutManager = LinearLayoutManager(this)
        rv_notification_new.addItemDecoration(VerticalSpaceItemDecoration(this, 25.dpToPx()))

        rv_notification_old.adapter = adapterOldNotification
        rv_notification_old.layoutManager = LinearLayoutManager(this)
        rv_notification_old.addItemDecoration(VerticalSpaceItemDecoration(this, 25.dpToPx()))

        // 리스트가 비어있으면 보이지 않아야한다.
        container_notification_new.visibility = View.GONE
        container_notification_old.visibility = View.GONE

        repository.getNewNotificationList().enqueue(
            object : Callback<List<AppNotification>> {
                override fun onFailure(call: Call<List<AppNotification>>, t: Throwable) {
                    toast(R.string.network_error)
                }

                override fun onResponse(call: Call<List<AppNotification>>, response: Response<List<AppNotification>>) {
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

                override fun onResponse(call: Call<List<AppNotification>>, response: Response<List<AppNotification>>) {
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
