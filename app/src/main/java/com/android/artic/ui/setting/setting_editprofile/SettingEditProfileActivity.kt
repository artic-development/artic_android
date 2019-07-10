package com.android.artic.ui.setting.setting_editprofile

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.media.MediaScannerConnection
import android.net.Uri
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
import com.android.artic.data.MyPage
import com.android.artic.data.MyPageRequest
import com.android.artic.logger.Logger
import com.android.artic.repository.ArticRepository
import com.android.artic.ui.BaseActivity
import com.android.artic.util.defaultHolderOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_setting_edit_profile.*
import kotlinx.android.synthetic.main.fragment_my_page.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import java.io.*
import java.util.*

class SettingEditProfileActivity : BaseActivity() {
    private val logger: Logger by inject()
    private val rxPermission by lazy { RxPermissions(this) }
    private val repository: ArticRepository by inject()
    private var btn: TextView?= null
    private var imageview: ImageView? = null
    lateinit var selectedPicUri: Uri

    private val GALLERY=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_edit_profile)

        edit_profile_finish_btn.isActivated=false
        setListener()

        edit_profile_img_change_btn.setOnClickListener{
            choosePhotoFromGallary()
        }

        repository.getMyInfo(
            successCallback = {
                logger.log("mypage success $it")
                edit_profile_name_et.setText(it.name)
              //  txt__my_page_email.text=it.id
                val img=it.profile_img
                edit_profile_img?.let{
                    Glide.with(this)
                        .load(img)
                        .apply(defaultHolderOptions)
                        .into(it)
                }
                edit_profile_myinfo_et.setText(it.my_info)
            },
            failCallback = {
                toast(R.string.network_error)
                logger.log("mypage edit fail")
            }
        )


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

            val name=edit_profile_name_et.text.toString()
            val my_info=edit_profile_myinfo_et.text.toString()

            val name_rb=RequestBody.create(MediaType.parse("text/plain"),name)
            val my_info_rb=RequestBody.create(MediaType.parse("text/plain"),my_info)

            //Encoding->이미지 파일을 서버로 전송 가능한 형태로 변환하는 부분
            val options=BitmapFactory.Options()
            val inputStream: InputStream =contentResolver.openInputStream(selectedPicUri)
            val bitmap=BitmapFactory.decodeStream(inputStream,null,options)
            val byteArrayOutputStream=ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream)
            val photoBody=RequestBody.create(MediaType.parse("image/jpg"),byteArrayOutputStream.toByteArray())

            val picture_rb=MultipartBody.Part.createFormData("img",File(selectedPicUri.toString()).name,photoBody)


//            val name_rb=RequestBody.create(
//                MediaType.parse("text/plain"),name)
//            val my_info_rb=RequestBody.create(MediaType.parse("text/plain"),my_info)



            repository.changeMyInfo(name_rb, my_info_rb,picture_rb,
                successCallback = {
                    logger.log("token data : $it")
                    toast("success")

                },
                failCallback = {
                    toast("mypage change fail")
                    logger.log("mypage change fail")
                }
            )

            finish()

        }
    }

    // 갤러리에서 이미지를 선택하면 onActivityResult() 메소드가 실행
    // 갤러리에서 이미지를 받아오기위한 권한 설정하고 설정이 되있으면 gallery 실행
    fun choosePhotoFromGallary() {
        rxPermission
            .request(Manifest.permission.READ_EXTERNAL_STORAGE)
            .subscribe{ granted ->
                if (granted) { // 외부 저장소 권한 설정
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
                selectedPicUri=contentURI
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

