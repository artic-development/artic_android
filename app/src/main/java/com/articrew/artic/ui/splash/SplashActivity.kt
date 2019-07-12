package com.articrew.artic.ui.splash

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.articrew.artic.R
import com.articrew.artic.auth.Auth
import com.articrew.artic.logger.Logger
import com.articrew.artic.repository.ArticRepository
import com.articrew.artic.ui.base.BaseSocialLoginActivity
import com.articrew.artic.ui.login.login_start.LoginStartActivity
import com.articrew.artic.ui.navigation.NavigationActivity
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_signup_start.*
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class SplashActivity : BaseSocialLoginActivity() {
    private val animationEnd = PublishSubject.create<Boolean>()
    private val autoLoginEnd = PublishSubject.create<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 데이터 캐싱은 어떻게 할까?

        // 적어도 스플래쉬 화면이 한 번의 애니메이션을 실행해야한다.
        // 스플래쉬 화면에서 자동 로그인을 요청해야 한다. 그리고 요청 결과에 따라 다음 화면으로 이동해야 한다.

        // 애니메이션이 끝나고 자동 로그인이 끝나면 방출하는 Observable 을 구성했다.
        Observable.combineLatest(
            animationEnd, autoLoginEnd, BiFunction<Boolean, Boolean, Boolean> { _, loginSuccess
                -> loginSuccess
            }
        ).subscribe {
            if (it) {
                // 자동 로그인이 성공되면 Navigation 화면으로 이동하자!
                // 전 activity 로그를 날려야한다.
                startActivity(intentFor<NavigationActivity>().clearTask().newTask())
            }
            else {
                // 자동 로그인이 실패되면 LoginStartActivity 화면으로 이동하자!
                // 전 activity 로그를 날려야한다.
                startActivity(intentFor<LoginStartActivity>().clearTask().newTask())
            }
        }.apply { addDisposable(this) }

        lottie_splash.addAnimatorListener(
            object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) { }

                override fun onAnimationEnd(animation: Animator?) {
                    animationEnd.onNext(true)
                }

                override fun onAnimationCancel(animation: Animator?) { }

                override fun onAnimationStart(animation: Animator?) { }
            }
        )

        auth.autoLogin(
            activity = this,
            successCallback = {
                successCallback()
            },
            failCallback = {
                failCallback(it)
            },
            errorCallback = {
                errorCallback(it)
            }
        )


    }

    override val failCallback: (String) -> Unit = { autoLoginEnd.onNext(false) }
    override val successCallback: () -> Unit = { autoLoginEnd.onNext(true) }
    override val errorCallback: (Throwable) -> Unit = { autoLoginEnd.onNext(false)}

    override fun onFacebookLoginCancel() {
        super.onFacebookLoginCancel()
        autoLoginEnd.onNext(false)
    }

    override fun onFacebookLoginError() {
        super.onFacebookLoginError()
        autoLoginEnd.onNext(false)
    }
}
