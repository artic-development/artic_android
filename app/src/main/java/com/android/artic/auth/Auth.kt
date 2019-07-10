package com.android.artic.auth

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.android.artic.auth.response.SigninResponse
import com.android.artic.auth.response.SignupResponse
import com.android.artic.data.auth.Signin
import com.android.artic.data.auth.Signup
import com.android.artic.logger.Logger
import com.android.artic.repository.remote.response.BaseResponse
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

    private val retrofit: AuthInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AuthInterface::class.java)
    }

    fun logout(
        callback: () -> Unit
    ) {
        clearLoginInfo()
        callback()
    }

    /**
     * it is called where app start
     * */
    fun autoLogin(
        successCallback: () -> Unit,
        failCallback: (String) -> Unit
    ) {
        val kind = pref.getInt("kind", -1)
        val www = pref.getString("www", "")
        val ww = pref.getString("ww", "")
        if (kind == -1) {
            failCallback("fail auto login")
            return
        }
        when (LoginKind.values()[kind]){
            LoginKind.EMAIL -> {
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
                        failCallback("${it.message}")
                    },
                    statusCallback = { _, success, message ->
                        if (!success) failCallback(message)
                    }
                )
            }
            LoginKind.KAKAO -> {

            }
        }
    }

    fun requestSignin(
        data: Signin,
        successCallback: (SigninResponse) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((status: Int, success: Boolean, message: String) -> Unit)? = null
        ) {
        retrofit.requestSignin("application/json",
            JsonObject().apply {
                addProperty("id", data.id)
                addProperty("pw", data.pw)
            }
        ).enqueue(
            object : Callback<BaseResponse<SigninResponse>> {
                override fun onFailure(call: Call<BaseResponse<SigninResponse>>, t: Throwable) {
                    failCallback?.invoke(t)
                }

                override fun onResponse(
                    call: Call<BaseResponse<SigninResponse>>,
                    response: Response<BaseResponse<SigninResponse>>
                ) {
                    response.body()?.let {
                        logger.log("from SERVER : \n$it")
                        statusCallback?.invoke(it.status, it.success, it.message)
                        it.data?.let(successCallback)
                        it.data?.let { res->
                            saveLoginInfo(
                                LoginKind.EMAIL, data.id, data.pw
                            )
                            token = res.token // 서버에서 받아온 토큰의 저장
                        }
                    }
                }

            }
        )
    }

    fun requestSignup(
        data: Signup,
        successCallback: (SignupResponse) -> Unit,
        failCallback: ((Throwable) -> Unit)? = null,
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
        kind: LoginKind,
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
        failCallback: ((Throwable) -> Unit)? = null,
        statusCallback: ((status: Int, success: Boolean, message: String) -> Unit)? = null): Callback<SERVER>
    {
        logger.log("call api")
        return object : Callback<SERVER> {
            override fun onFailure(call: Call<SERVER>, t: Throwable) {
                failCallback?.invoke(t)
            }

            override fun onResponse(
                call: Call<SERVER>,
                response: Response<SERVER>
            ) {
                response.body()?.let {
                    logger.log("from SERVER : \n$it")
                    statusCallback?.invoke(it.status, it.success, it.message)
                    it.data?.let(successCallback)
                }
            }

        }
    }
}