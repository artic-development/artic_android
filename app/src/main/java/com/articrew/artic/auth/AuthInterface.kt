package com.articrew.artic.auth

import com.articrew.artic.auth.response.SigninResponse
import com.articrew.artic.auth.response.SignupResponse
import com.articrew.artic.repository.remote.response.BaseResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * 데이터를 처리하기 위한 것이 아닌 통신을 모아놓음 isLiked 로그인, 회원가입 등
 * @author greedy0110
 * */
interface AuthInterface{
    /**
     * https://github.com/artic-development/artic_server/wiki/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85
     * body
     *      id: String
     *      pw: String
     *      birth: String ("2019-07-05")
     *      name: String
     * @author greedy0110
     * */
    @POST("/auth/signup")
    fun requestSignup(
        @Header("Content-Type") contentType: String,
        @Body body: JsonObject
    ): Call<BaseResponse<SignupResponse>>

    /**
     * https://github.com/artic-development/artic_server/wiki/%EB%A1%9C%EA%B7%B8%EC%9D%B8
     * body
     *      id: String
     *      pw: String
     * @author greedy0110
     * */
    @POST("/auth/signin")
    fun requestSignin(
        @Header("Content-Type") contentType: String,
        @Body body: JsonObject
    ): Call<BaseResponse<SigninResponse>>


    /**
     *
     * body
     *      id: String
     *      email: String?
     *      name: String
     *      img: String?
     * @author greedy0110
     * */
    @POST("/auth/facebook")
    fun requestFacebookLogin(
        @Header("Content-Type") contentType: String = "application/json",
        @Body body: JsonObject
    ): Call<BaseResponse<String>>
}