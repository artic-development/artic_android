package com.articrew.artic.ui.base

import android.content.Intent
import android.os.Bundle
import com.articrew.artic.R
import com.articrew.artic.auth.Auth
import com.articrew.artic.auth.facebook.FacebookLoginBody
import com.articrew.artic.auth.facebook.UserRequest
import com.articrew.artic.ui.navigation.NavigationActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

/**
 * facebook login button -> LoginManager.getInstance().logInWithReadPermissions(this, mutableListOf("public_profile", "email"))
 * implement only facebook social login...
 * @author greedy0110
 * */
open class BaseSocialLoginActivity : BaseActivity() {
    protected val auth: com.articrew.artic.auth.Auth by inject()
    private lateinit var callbackManager: CallbackManager
    private lateinit var accessTokenTracker: AccessTokenTracker
    private lateinit var profileTracker: ProfileTracker

    companion object {
        val facebookReadPermission = listOf("public_profile", "email")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFacebookLogin()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        accessTokenTracker.stopTracking()
        profileTracker.stopTracking()
    }

    // https://developers.facebook.com/docs/facebook-login/android 참고 자료
    private fun setFacebookLogin() {
        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                // 로그인에 성공하면 토큰을 사용해서 id, email, name, img 을 쿼리한다.
                // 쿼리가 왼료되면 그 데이터를 사용해 artic 서버와 통신한다.
                // artic 서버에서 오는 토큰 값을 Auth.token으로 설정하고 NavigationActivity 로 이동한다.
                UserRequest.makeUserRequest(object : GraphRequest.Callback{
                    override fun onCompleted(response: GraphResponse?) {
                        response?.jsonObject?.let {
                            logger.log("json: $it\n")
                            val picture = it.getJSONObject("picture")?.getJSONObject("data")?.getString("url")
                            var email: String? = null
                            if (it.has("email")) // 페이스북 정책상 이메일없이 로그인 하는 경우가 있을 수 있다.
                                email = it.getString("email")
                            val id = it.getString("id")
                            val name = it.getString("name")
                            logger.log("id: $id, name: $name, email: $email\npicture: $picture")
                            auth.requestFacebookLogin(
                                data = FacebookLoginBody(
                                    id = id, email = email, name = name, img = picture
                                ),
                                successCallback = { successCallback() },
                                failCallback = failCallback,
                                statusCallback = { _, success, message ->
                                    if (!success) toast(message)
                                },
                                errorCallback = errorCallback
                            )
                        }
                    }
                })
                logger.log("facebook login success ${result?.accessToken} \n${result?.recentlyDeniedPermissions}\n${result?.recentlyGrantedPermissions}")
            }

            override fun onCancel() {
                logger.log("facebook login cancel")
                onFacebookLoginCancel()
            }

            override fun onError(error: FacebookException?) {
                logger.log("facebook login success $error")
                onFacebookLoginError()
            }
        })

        // 실제로 facebook token 을 사용해 통신할 일은 없기때문에 크게 의미있진 않다.
        accessTokenTracker = object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
                logger.log("token change")
            }
        }

        // 프로필 변경됬을때 서버에 알려줄 수 있긴한데... 흠...
        profileTracker = object : ProfileTracker() {
            override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
                logger.log("facebook ${oldProfile.toString()} ${currentProfile.toString()}")
            }
        }

        // 자동 로그인 시 이거 사용하면 된다.
        //LoginManager.getInstance().logIn(this, mutableListOf("public_profile", "email"))

        // 페이스북 로그아웃 시 이거 사용하면 된다.
        //LoginManager.getInstance().logOut()
    }

    open val successCallback: () -> Unit = {
        startActivity(intentFor<NavigationActivity>().clearTask().newTask())
    }
    open val failCallback: (String) -> Unit = {
        logger.error("facebook login fail $it")
    }
    open val statusCallback: () -> Unit = {
    }
    open val errorCallback: (Throwable) -> Unit = {}

    open fun onFacebookLoginCancel() {}
    open fun onFacebookLoginError() {}
    open fun onFacebookLoginSuccess() {}

    //    private fun setKakaoLogin() {
//        Session.getCurrentSession().addCallback(object : ISessionCallback{
//            override fun onSessionOpenFailed(exception: KakaoException?) {
//                logger.error("kakao session open failed $exception")
//            }
//
//            override fun onSessionOpened() {
//                logger.log("kakao session open")
//                requestMe()
//            }
//        })
//
//         카카오 자동로그인 시 이거 사용하면 된다.
//        Session.getCurrentSession().checkAndImplicitOpen()
//
//         카카오 로그아웃 시 이거 사용하면 된다.
//        UserManagement.getInstance().requestLogout(object : LogoutResponseCallback(){
//            override fun onCompleteLogout() {
//                logger.log("kakao logout")
//            }
//        })
//    }
//
//    private fun requestMe() {
//        val keys = arrayListOf("properties.nickname", "properties.profile_image", "kakao_account.email", "kakao_account.id")
//        UserManagement.getInstance().me(keys,
//            object : MeV2ResponseCallback() {
//                override fun onSuccess(result: MeV2Response?) {
//                    logger.log("$result")
//                }
//
//                override fun onSessionClosed(errorResult: ErrorResult?) {
//                    logger.error("$errorResult")
//                }
//            })
//    }
}