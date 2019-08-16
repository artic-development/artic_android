package com.articrew.artic.ui.edit_archive

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.articrew.artic.R
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.ui.new_archive.MakeNewArchiveData
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.activity_make_new_archive.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

/**
 * must have
 * archiveId : Int
 * archiveTitle : String
 * archiveImg : String?
 * */
class EditArchiveActivity : BaseActivity() {
    private val repository: ArticRepository by inject()

    var archiveId = -1
    var archiveTitle = ""
    var archiveImg = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_new_archive)

        archiveId = intent.getIntExtra("archiveId", -1)
        archiveTitle = intent.getStringExtra("archiveTitle")
        archiveImg = intent.getStringExtra("archiveImg")

        txt_make_new_archive_title.setText(R.string.edit_archive) // 기본값 설정
        et_act_make_new_archive_title.setText(archiveTitle) // 초기값 설정

        et_act_make_new_archive_title.textChanges().subscribe {
            btn_act_make_new_archive_complete.isEnabled = it.isNotEmpty()
            btn_act_make_new_archive_complete.setTextColor(if (it.isNotEmpty()) Color.parseColor("#4f80ff") else Color.parseColor("#707070"))
        }.apply { addDisposable(this) }

        btn_act_make_new_archive_complete.setOnClickListener {
            // 서버 api로 이름 수정 요청
            val title = et_act_make_new_archive_title.text.toString()

            repository.updateMyArchive(archiveId, MakeNewArchiveData(
                title = title,
                img = archiveImg,
                categoryIdx = 0
            )).subscribe({
                toast(it)
                setResult(Activity.RESULT_OK, Intent().apply { putExtra("archiveTitle", title) })
                finish()
            }, {
                logger.error("edit archive activity error ${it.message}")
            })
        }
    }
}
