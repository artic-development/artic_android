package com.articrew.artic

import androidx.test.rule.ActivityTestRule
import com.articrew.artic.ui.login.login_start.LoginStartActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class FacebookLoginTest {
    @get:Rule val activityRule = ActivityTestRule(LoginStartActivity::class.java)

//    @Mock
//    lateinit var mockFacebookLogin: FacebookLoginCallback

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun successFacebookLogin() {
        buttonClick(R.id.btn_login_start_facebook_login)

        // TODO 실제 facebook api에 접근해서 로그인까지 시간이 걸리거나. 별도의 로그인 작업을 해주어야 한다.
        // 1. https://developers.facebook.com/ 에서 테스트 계정을 만든다.
        // 2. 만든 계정으로 로그인을 시도하자. (HOW???)

        //verify(mockFacebookLogin).onSuccess()
    }

    @Test
    fun successFacebookLoginWhenAlreadyLogedIn() {
        buttonClick(R.id.btn_login_start_facebook_login)

        // TODO 실제 facebook api에 접근해서 로그인까지 시간이 걸리거나. 별도의 로그인 작업을 해주어야 한다.
        // 1. 예전 계정으로 로그인을 시도하자. (HOW???)

        //verify(mockFacebookLogin).onSuccess()
    }
}