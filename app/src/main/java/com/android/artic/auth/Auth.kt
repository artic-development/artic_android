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
import io.reactivex.Observable
import khronos.toString
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Auth (
    private val context: Context,
    private val logger: Logger
) {
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
        val pref = context.getSharedPreferences("login", MODE_PRIVATE)
        pref.edit().apply {
            putString("www", "")
            putString("ww", "")
        }.apply()
        callback()
    }

    /**
     * it is called where app start
     * */
    fun autoLogin(
        successCallback: (SigninResponse) -> Unit,
        failCallback: () -> Unit
    ) {
        val pref = context.getSharedPreferences("login", MODE_PRIVATE)
        val www = pref.getString("www", "")
        val ww = pref.getString("ww", "")
        if (www.isNullOrEmpty() || ww.isNullOrEmpty()) {
            failCallback()
            return
        }
        requestSignin(
            data = Signin(www,ww),
            successCallback = successCallback,
            failCallback = {
                failCallback()
            },
            statusCallback = { _, success, _ ->
                if (!success) failCallback()
            }
        )
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
                            val pref = context.getSharedPreferences("login", MODE_PRIVATE)
                            pref.edit().apply {
                                putString("www", data.id)
                                putString("ww", data.pw)
                            }.apply()
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

    fun requestKakaoLogin(

    ) {

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