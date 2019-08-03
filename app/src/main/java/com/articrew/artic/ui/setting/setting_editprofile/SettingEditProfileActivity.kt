package com.articrew.artic.ui.setting.setting_editprofile

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import com.articrew.artic.R
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseActivity
import com.articrew.artic.util.defaultHolderOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_setting_edit_profile.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import java.io.*

class SettingEditProfileActivity : BaseActivity() {
    private val rxPermission by lazy { RxPermissions(this) }
    private val repository: ArticRepository by inject()
    private var btn: TextView?= null
    private var imageview: ImageView? = null
    var selectedPicUri: Uri? = null
    var originalImage: String? = null

    // 사용자 정보
    var name = "default"
    var myInfo = "default"


    // 완료 버튼 활성화 체크 플래그
    var flag = 0 // 활성화가 되면 1, 활성화가 안되면 0

    private val GALLERY=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_edit_profile)

//        edit_profile_finish_btn.isEnabled=false
        edit_profile_finish_btn.textColor = Color.parseColor("#707070") // "완료" 버튼 초기 색상은 회색
        setListener()

        edit_profile_img_change_btn.setOnClickListener{
//            edit_profile_finish_btn.textColor = Color.parseColor("#4f80ff")
            choosePhotoFromGallary()
        }

        repository.getMyInfo()
            .subscribe(
                {
                    // 추가
                    name = it.name
                    it.my_info?.let {my_info ->
                        myInfo = my_info
                    }
                    //

                    logger.log("mypage success $it")
                    edit_profile_name_et.setText(it.name)
                    //  txt__my_page_email.text=it.id
                    val img =it.profile_img
                    originalImage = img
                    edit_profile_img?.let{
                        Glide.with(this)
                            .load(img)
                            .apply(defaultHolderOptions)
                            .into(it)
                    }
                    edit_profile_myinfo_et.setText(it.my_info)
                },
                {
                    logger.error("setting edit profile activity get my info error")
                    toast(R.string.network_error)
                }
            ).apply { addDisposable(this) }
    }

    private fun setListener() {

        edit_profile_name_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //  edit_profile_finish_btn.setTextColor(ContextCompat.getColor(btn , R.color.soft_blue))
//                edit_profile_finish_btn.isEnabled = true
//                edit_profile_finish_btn.textColor = Color.parseColor("#4f80ff")
//                flag = 1
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                edit_profile_finish_btn.textColor = Color.parseColor("#707070")
//                flag = 0
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edit_profile_name_et.text.toString() != name) { // 정보가 바꼈다는 뜻
                    edit_profile_finish_btn.textColor = Color.parseColor("#4f80ff")
                    flag = 1
                }
                else {
                    edit_profile_finish_btn.textColor = Color.parseColor("#707070")
                    flag = 0
                }
            }

        })

        edit_profile_myinfo_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
//                edit_profile_finish_btn.textColor = Color.parseColor("#4f80ff")
//                flag = 1
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                edit_profile_finish_btn.textColor = Color.parseColor("#707070")
//                flag = 0
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edit_profile_myinfo_et.text.toString() != myInfo) { // 정보가 바뀜
                    edit_profile_finish_btn.textColor = Color.parseColor("#4f80ff")
                    flag = 1
                }
                else {
                    edit_profile_finish_btn.textColor = Color.parseColor("#707070")
                    flag = 0
                }
            }

        })

        edit_profile_finish_btn.setOnClickListener {
            if (flag == 1) {
                logger.log("edit profile finish btn")
                if (selectedPicUri != null) {
                    selectedPicUri?.let {
                        logger.info("select pic uri")
                        //Encoding->이미지 파일을 서버로 전송 가능한 형태로 변환하는 부분
                        val options=BitmapFactory.Options()
                        val inputStream: InputStream? = contentResolver.openInputStream(it)
                        BitmapFactory.decodeStream(inputStream,null,options)?.let {bitmap ->
                            changeWithBitmap(bitmap)
                        }
                    }
                }
                else {
                    logger.log("original image encode")
                    Glide.with(this).asBitmap().load(originalImage)
                        .into(object : CustomTarget<Bitmap>() {
                            override fun onLoadCleared(placeholder: Drawable?) {
                                logger.log("on load cleared")
                            }

                            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                logger.log("oh yes bitmap resource is ready!")
                                changeWithBitmap(resource)
                            }
                        }
                        )
                }
            }
            else {
                toast("정보를 바꿔주세요")
            }
        }
    }

    private fun changeWithBitmap(bitmap: Bitmap){
        //Encoding->이미지 파일을 서버로 전송 가능한 형태로 변환하는 부분
        val byteArrayOutputStream=ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream)
        val photoBody=RequestBody.create(MediaType.parse("image/*"),byteArrayOutputStream.toByteArray())

        val picture_rb=MultipartBody.Part.createFormData("img","temp", photoBody)

        val name=edit_profile_name_et.text.toString()
        val my_info=edit_profile_myinfo_et.text.toString()

        val name_rb=RequestBody.create(MediaType.parse("text/plain"),name)
        val my_info_rb=RequestBody.create(MediaType.parse("text/plain"),my_info)

        repository.changeMyInfo(name_rb, my_info_rb, picture_rb)
            .subscribe {
                logger.log("token data : $it")
            }.apply { addDisposable(this) }
        finish()
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

                            edit_profile_finish_btn.textColor = Color.parseColor("#4f80ff")
                            flag = 1

                            return false
                        }
                    })
                    .into(edit_profile_img)
            }
        }

    }
}

