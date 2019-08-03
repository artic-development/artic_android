package com.articrew.artic.ui.new_archive

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.articrew.artic.R
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_make_new_archive.*
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class MakeNewArchiveActivity : BaseActivity() {
    private val repository: ArticRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_new_archive)

        setCompleteBtn()
    }

    private fun setCompleteBtn() {
        // @수민) 텍스트가 쳐질 때마다 확인해서 사용자가 타이틀을 입력했으면 완료 버튼이 활성화되도록
        et_act_make_new_archive_title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (et_act_make_new_archive_title.text.toString() != "" ) {
                    txt_act_make_new_archive_complete.setTextColor(Color.parseColor("#4f80ff"))
                }
                else {
                    txt_act_make_new_archive_complete.setTextColor(Color.parseColor("#707070"))
                }
            }
        })

        // @수민) 완료 버튼 리스너
        txt_act_make_new_archive_complete.setOnClickListener {
            if (txt_act_make_new_archive_complete.currentTextColor == Color.parseColor("#4f80ff")) {
                val title = et_act_make_new_archive_title.text.toString()
                repository.addMyArchive(MakeNewArchiveData(title, null, 6))
                    .subscribe(
                        {
                            toast("아카이브 생성 완료")
                        },
                        {
                            logger.error("make new archive activity add my archive error")
                            toast(R.string.network_error)
                        }
                    ).apply { addDisposable(this) }

                finish()
            }
        }
    }
}
