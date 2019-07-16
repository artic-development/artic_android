# artic_android

2019.07.13 작성자 신승민
-------------------

# 프로젝트 기간 및 참여자

- 2019.06-2019.07
- 참여자 : 송경희, 신승민, 조수민

# 개발 환경

- Android Studio

# 사용 라이브러리

- [glide](https://github.com/bumptech/glide) : 이미지 로딩을 위해 사용 (url 로드, uri 로드, placeholder 사용해 일관적인 image 처리)
- [koin](https://github.com/InsertKoinIO/koin) : 생성을 추상화 하기 위해서 사용 (repository, auth, logger 등을 singleton 객체로 쉽게 전체 프로젝트에서 접근)
- [retrofit](https://square.github.io/retrofit/) + [gson](https://github.com/google/gson) : Json 데이터를 이용한 rest 서버 통신을 위해 사용 (Callback<T> 를 람다를 이용해 만드는 함수 설계로 일관적인 code 개발 구현)
- [rxjava](https://github.com/ReactiveX/RxJava) : 비동기적인 작업을 추적하기 위해 부분 사용 (비동기 코드를 유연하게 처리)
- [anko](https://github.com/Kotlin/anko) : android framwork 의 보일러 플레이트 코드를 일부 제거하기 위해 사용 (startActivity, intentFor)
- [khronous](https://github.com/hotchemi/khronos) : Date 처리를 위한 라이브러리 직관적인 Date, String 변환
- [google material](https://github.com/material-components/material-components-android) : google material design 적용을 위해 사용 (Notification 의 배지를 보여주기 위해 채용)
- facebook : 페이스북 로그인을 위해 사용 (개발자 등록이 필요)
- [lottie](https://github.com/material-components/material-components-android) : 애프터이펙트로 만든 애니메이션을 적용하기 위해 사용

# 패키지별 설명
- ui : Activity, Fragment, Adapter, Holder 등 화면을 그려주는 코드
    - adapter : 여러 Activity, Fragment에서 공통적으로 사용하는 Adapter 를 모아놓은 곳
    - 기능별 화면 
- data : ui 에서 화면을 그리는데 사용하는 데이터 모아놓는 코드
- repository : data 를 비동기적으로 관리하는 코드
    - local : 로컬에서 저장된 코드 (현재는 mock 데이터를 방출하는 코드)
    - remote : 외부 저장소에서 데이터를 받아오는 코드 (현재는 retrofit 을 사용해서 rest 통신)
- auth : 로그인, 로그아웃, 자동로그인, token 관리하는 auth 코드

## util 패키지
- util : extenstion 함수를 포함한 어느 코드에서나 사용가능한 유틸리티 코드
- logger : 프로젝트 로깅을 묶어놓은 유틸리티 코드

# 프로젝트 개발 플로우
1. 디자인을 기반으로 필요한 data 클래스를 설게한다.
2. data 클래스를 사용하여 ui 코드를 설계한다.
3. repository.local 을 사용해서 더미데이터를 가지고 ui 코드를 개발한다.
4. 서버 명세를 보고 repository.remote 를 설계한다.
5. repository.remote 데이터를 data package 로 변환하는 방법을 설계한다.
6. repostiroy.remote 를 개발 완료한다.
7. ui 테스트를 진행한다.

# 비동기 처리

**repository 요청은 전부 비동기 통신이다.** 라는 컨셉으로 프로젝트를 제작 진행하였다.

비동기 처리를 위해 선택한 방법은 **callback** 방법이다. ~~(rxjava 가 아닌게 아쉽다. 리팩토링하자.)~~

서버에서 받아오는 기본 데이터를 다음과 같이 설계했다.

```kotlin
data class BaseResponse<DATA>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: DATA?
)
```

그래서 전체적인 reppsitory 코드의 외형은 다음과 같다.


```kotlin
// 모든 callback 은 nullable 이다. 필요한 것만 등록해서 사용하자.
repository.getSomething<UI, SERVER>(
    // BaseResponse.success 가 true 일때 발생하는 콜백이다. UI 데이터를 사용하는 callback 함수이다. ui 코드에서 등록하면 SERVER 데이터가 아니라 UI 데이터를 받아서 사용할 수 있다.
    successCallback: (UI) -> Unit, 

    // BaseResponse.success 가 false 일때 발생하는 콜백이다. BaseResponse.message 를 인자로 넘겨받아서 서버에서 오는 실패 메시지를 확인 할 수 있다.
    failCallback: (String) -> Unit, 

    // BaseResponse.status 값에 따라서 값을 처리하고 싶을때 등록해서 사용하면 된다. BaseResponse.success 값에 상관없이 호출된다.
    statusCallback: (Int, Boolean, String) -> Unit, 

    // Retrofit.Callback<>.onFailure 가 호출되면 발생하는 콜백이다. 네트워크 통신 에러를 처리하자.
    errorCallback: (Throwable) -> Unit 
)
```

따라서 모든 repository 코드는 **선언적 이다.** callback을 등록하고 바로 다음 호출로 코드흐름이 넘어간다. 비동기적으로 데이터를 받아오면 등록한 callback이 발생한다.

이를 통해서 ui 코드는 server 데이터와 무관하게 제작할 수 있다. ui 코드에서 실제 사용 예시는 다음과 같다.

```kotlin
// koin 에게 repository 생성을 넘겨서 ui 코드는 생성코드를 알지 않아도 된다.
private val repository: ArticRepository by inject()

override fun onCreate() {
    ............
    ... somecode

    repository.getSomething(
        // data package 에 있는 정보만 알아도 ui 코드 개발이 가능하다.
        successCallback = { data ->
            updateUI(data)
        },
        failCallback = { message ->
            toast(message)
            updateUI(null)
        }
    )

    ... somecode
    ............
}
```

## 문제점

둘 이상의 비동기 통신을 유연하게 처리하기 어렵다. 따라서 해당 부분은 rxjava를 사용하여 부분적으로 커버했다.

# UI BaseClass

## BaseActivity
- 공통된 상태바 설정
- rxjava 사용을 위해 compositreDisposable 관리
- Logging 을 위한 Logger 객체의 초기화

## BaseFragment
- ViewPager 에서 사용할 콜백 함수들 추가 (BaseOnPageChangeListner 를 등록해야한다.)
  - onResumeFragment() : ViewPager를 사용해 해당 프래그먼트 탭을 선택했을때 발동한다.
  - onPauseFragment() : ViewPager를 사용해 해당 프래그먼트 탭을 벗어날때 발동한다.
- requestTopScroll() : 요청하면 Fragment를 구성하는 ScrollView를 최상위로 올려보낸다. bottom navigation의 반응성을 위해 추가하였다.

## BaseSocialLoginActivity
- Auth 객체의 초기화
- facebook Login 을 위한 코드들을 등록

# 문제점

## wrap_content 되지 않는 ViewPager
**코드 위치** : ui.mypage.mypage.MyPageFragment & ui.navigation.NavigationActivity

**문제 상황** : MyPageMeFragment와 MyPageScrapFragment를 Item으로 하는 ViewPager를 개발하였다. 하지만 NavigationActivity 에서 **bottom navigation 을 구성하기 위한 ViewPager의 offscreenPageLimit 세팅값을 2로 세팅**하고 4번째 탭인 MyPageFragment를 로드하면 처음에는 ViewPager 가 정상 작동했다가. **첫번째, 두번째 탭으로 이동후 다시 돌아오면** MyPageFragment의 ViewPager가 0 이 된다.

**일단 해결 법** : **NavigationActivity ViewPager offscreenPageLimit 를 4로 설정**하여 해결하였다. 하지만 이로 인해 데이터를 한번에 로드하고 관리하는 양이 매우 늘어나서 최적화하는데 방해가 된다.

## 비동기적으로 추가되는 HomeFragment의 하위 Fragment 들
**코드 위치** : ui.home.HomeFragment & ui.home.*

**문제 상황** : 어플리케이션 맨 처음 시작시에 서버의 응답에 따라 Home 화면이 달라지는데, 이를 위해 비동기적으로 fragment 들을 HomeFragment에 추가해주고 있다. 하지만 만약 **서버에서 로드해야하는 데이터가 많아진다면 그 많은 프래그먼트를 그리는데 오래걸리고 메모리 차지가 심할 것이다.** 이를 위해 **FragmentStatePager**와 같이 죽 늘어지는 Fragment들을 관리하는 라이브러리를 찾거나 최적화 해야한다.

## 같은 api call을 여러번 하는 문제
**코드 위치** : ui 코드 전반 & repository 코드 전반

**문제 상황** : Home 화면을 그리기 위해 호출한 api call 들을 각 화면 들어가면 다시 호출한다. 이로써 **같은 정보를 여러번 요청하여 데이터 낭비와 로딩 속도 저하가 발생**한다. 캐싱 **시스템을 구축하여 데이터 낭비를 줄이고 캐싱된 데이터에 바로 접근하여 화면을 그려주어 로딩 속도를 빠르게 만들 수 있을 것이다.**.
