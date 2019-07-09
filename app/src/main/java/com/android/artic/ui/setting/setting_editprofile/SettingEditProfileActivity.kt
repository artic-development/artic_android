package com.android.artic.ui.setting.setting_editprofile

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.MediaScannerConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.android.artic.R
import com.android.artic.logger.Logger
import com.android.artic.ui.BaseActivity
import com.android.artic.util.defaultHolderOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_setting_edit_profile.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class SettingEditProfileActivity : BaseActivity() {
    private val logger: Logger by inject()
    private val rxPermission by lazy { RxPermissions(this) }

    private val GALLERY=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_edit_profile)

        edit_profile_finish_btn.isActivated=false
        setListener()

        edit_profile_img_change_btn.setOnClickListener{
            choosePhotoFromGallary()
        }
    }

    private fun setListener() {

        edit_profile_name_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //  edit_profile_finish_btn.setTextColor(ContextCompat.getColor(btn , R.color.soft_blue))
                edit_profile_finish_btn.isActivated = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                edit_profile_finish_btn.isActivated = false

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        edit_profile_myinfo_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                edit_profile_finish_btn.isActivated = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                edit_profile_finish_btn.isActivated = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        edit_profile_finish_btn.setOnClickListener {
            finish()
        }
    }

    // 갤러리에서 이미지를 선택하면 onActivityResult() 메소드가 실행
    // 갤러리에서 이미지를 받아오기위한 권한 설정하고 설정이 되있으면 gallery 실행
    fun choosePhotoFromGallary() {
        rxPermission
            .request(Manifest.permission.READ_EXTERNAL_STORAGE)
            .subscribe{ granted ->
                if (granted) { // 외ㅂ 저장소 권한 설정
                    val galleryIntent = Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                    startActivityForResult(galleryIntent, GALLERY)
                }
                else { // 권한 설정 거부
                    toast(R.string.read_storage_permission_warning)
                }
            }.apply { addDisposable(this) }
    }


    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == GALLERY)
        {
            data?.data?.let {contentURI ->
                logger.log("$contentURI ${contentURI.path}")
                Glide.with(this)
                    .load(contentURI)
                    .apply(defaultHolderOptions)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            logger.error("image load fail from gallery $e")
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            logger.log("image load success from gallery")
                            return false
                        }
                    })
                    .into(edit_profile_img)
            }
        }

    }
}

