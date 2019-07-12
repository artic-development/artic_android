package com.articrew.artic.auth

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.articrew.artic.auth.facebook.FacebookLoginBody
import com.articrew.artic.auth.response.SigninResponse
import com.articrew.artic.auth.response.SignupResponse
import com.articrew.artic.data.auth.Signin
import com.articrew.artic.data.auth.Signup
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.remote.response.BaseResponse
import com.facebook.login.LoginManager
import com.google.gson.JsonObject
import khronos.toString
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * it must need parameter activity!!!
 * @author greedy0110
 * */
class Auth (
    private val context: Context,
    private val logger: Logger
) {
    private val pref: SharedPreferences by lazy { context.getSharedPreferences("login", MODE_PRIVATE) }

    enum class LoginKind {
        EMAIL, KAKAO, FACEBOOK
    }

    companion object {
        val BASE_URL = "http://15.164.11.203:3000"
        var token: String? = null
    }

    private val retrofit: com.articrew.artic.auth.AuthInterface by lazy {
        Retrofit.Builder()
            .baseUrl(com.articrew.artic.auth.Auth.Companion.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(com.articrew.artic.auth.AuthInterface::class.java)
    }

    fun logout(
        callback: () -> Unit
    ) {
        clearLoginInfo()
        callback()
    }

    /**
     * it is called where app start
     * @param activity 소셜 로그인 할 때 필요하다!
     * @author greedy0110
     * */
    fun autoLogin(
        activity: Activity,
        successCallback: () -> Unit,
        failCallback: (String) -> Unit,
        errorCallback: ((Throwable) -> Unit)? = null
    ) {
        val kind = pref.getInt("kind", -1)
        val www = pref.getString("www", "")
        val ww = pref.getString("ww", "")
        if (kind == -1) {
            failCallback("fail auto login")
            return
        }
        when (com.articrew.artic.auth.Auth.LoginKind.values()[kind]){
            com.articrew.artic.auth.Auth.LoginKind.EMAIL -> {
                if (www.isNullOrEmpty() || ww.isNullOrEmpty()) {
                    failCallback("fail auto login")
                    return
                }

                requestSignin(
                    data = Signin(www,ww),
                    successCallback = {
                        successCallback()
                    },
                    failCallback = {
                        failCallback(it)
                    },
                    statusCallback = { _, success, message ->
                        if (!success) failCallback(message)
                    },
                    errorCallback = errorCallback
                )
            }
            com.articrew.artic.auth.Auth.LoginKind.KAKAO -> {
                // 아직 미구현 상태이다. 나중에 카카오 로그인이 필요할때 이부분에 코드를 추가해줄 것
            }
            com.articrew.artic.auth.Auth.LoginKind.FACEBOOK -> {
                LoginManager.getInstance().logIn(activity, mutableListOf("public_profile", "email"))
            }
        }
    }

    fun requestSignin(
        data: Signin,
        successCallback: (SigninResponse) -> Unit,
        failCallback: ((String) -> Unit)? = null,
        statusCallback: ((status: Int, success: Boolean, message: String) -> Unit)? = null,
        errorCallback: ((Throwable) -> Unit)? = null
        ) {
        retrofit.requestSignin("application/json",
            JsonObject().apply {
                addProperty("id", data.id)
                addProperty("pw", data.pw)
            }
        ).enqueue(
            object : Callback<BaseResponse<SigninResponse>> {
                override fun onFailure(call: Call<BaseResponse<SigninResponse>>, t: Throwable) {
                    errorCallback?.invoke(t)
                }

                override fun onResponse(
                    call: Call<BaseResponse<SigninResponse>>,
                    response: Response<BaseResponse<SigninResponse>>
                ) {
                    response.body()?.let {
                        logger.log("from SERVER : \n$it")
                        statusCallback?.invoke(it.status, it.success, it.message)
                        if (it.success) {
                            it.data?.let { res ->
                                saveLoginInfo(
                                    com.articrew.artic.auth.Auth.LoginKind.EMAIL, data.id, data.pw
                                )
                                com.articrew.artic.auth.Auth.Companion.token = res.token // 서버에서 받아온 토큰의 저장
                            }
                            it.data?.let(successCallback)
                        }
                        else {
                            failCallback?.invoke(it.message)
                        }
                    }
                }

            }
        )
    }

    fun requestFacebookLogin(
        data: FacebookLoginBody,
        successCallback: (String) -> Unit,
        failCallback: ((String) -> Unit)? = null,
        statusCallback: ((status: Int, success: Boolean, message: String) -> Unit)? = null,
        errorCallback: ((Throwable) -> Unit)? = null
    ) {
        retrofit.requestFacebookLogin(
            body = JsonObject().apply {
                addProperty("id", data.id)
                addProperty("email", data.email)
                addProperty("name", data.name)
                addProperty("img", data.img)
            }
        ).enqueue(
            object : Callback<BaseResponse<String>> {
                override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                    errorCallback?.invoke(t)
                }

                override fun onResponse(
                    call: Call<BaseResponse<String>>,
                    response: Response<BaseResponse<String>>
                ) {
                    response.body()?.let {
                        logger.log("from SERVER : \n$it")
                        statusCallback?.invoke(it.status, it.success, it.message)
                        if (it.success) {
                            it.data?.let { res ->
                                saveLoginInfo(
                                    com.articrew.artic.auth.Auth.LoginKind.FACEBOOK, data.id, null
                                )
                                logger.log("get facebook login token : $res")
                                com.articrew.artic.auth.Auth.Companion.token = res // 서버에서 받아온 토큰의 저장
                            }
                            it.data?.let(successCallback)
                        }
                        else {
                            failCallback?.invoke(it.message)
                        }
                    }
                }

            }
        )
    }

    fun requestSignup(
        data: Signup,
        successCallback: (SignupResponse) -> Unit,
        failCallback: ((String) -> Unit)? = null,
        statusCallback: ((status: Int, success: Boolean, message: String) -> Unit)? = null) {
        retrofit.requestSignup("application/json",
            JsonObject().apply {
                addProperty("id", data.id)
                addProperty("pw", data.pw)
                addProperty("birth", data.birth.toString("yyyy-MM-dd"))
                addProperty("name", data.name)
            }
        ).enqueue(
            createFromRemoteCallback(
                successCallback, failCallback, statusCallback
            )
        )
    }

    private fun saveLoginInfo(
        kind: com.articrew.artic.auth.Auth.LoginKind,
        id: String?,
        pw: String?
    ) {
        pref.edit().apply {
            putInt("kind", kind.ordinal)
            putString("www", id)
            putString("ww", pw)
        }.apply()
    }

    private fun clearLoginInfo(){
        pref.edit().apply {
            putInt("kind", -1)
            putString("www", "")
            putString("ww", "")
        }.apply()
    }

    /**
     * @param mapper transform server data to UI data
     * @param successCallback will be called when server interaction success
     * @param failCallback will be called when server interaction fail
     * @param statusCallback will be called when server interaction with no error
     * @author greedy0110
     * */
    private fun <T,SERVER: BaseResponse<T>>createFromRemoteCallback(
        successCallback: (T) -> Unit,
        failCallback: ((String) -> Unit)? = null,
        statusCallback: ((status: Int, success: Boolean, message: String) -> Unit)? = null,
        errorCallback: ((Throwable) -> Unit)? = null
    ): Callback<SERVER>
    {
        logger.log("call api")
        return object : Callback<SERVER> {
            override fun onFailure(call: Call<SERVER>, t: Throwable) {
                errorCallback?.invoke(t)
            }

            override fun onResponse(
                call: Call<SERVER>,
                response: Response<SERVER>
            ) {
                response.body()?.let {
                    logger.log("from SERVER : \n$it")
                    statusCallback?.invoke(it.status, it.success, it.message)
                    if (it.success)
                        it.data?.let(successCallback)
                    else
                        failCallback?.invoke(it.message)
                }
            }

        }
    }
}