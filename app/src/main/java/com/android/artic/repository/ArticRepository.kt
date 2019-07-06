package com.android.artic.repository

import com.android.artic.data.Archive
import com.android.artic.data.Article
import com.android.artic.data.Category
import com.android.artic.data.notification.*
import khronos.Dates
import khronos.days
import khronos.minutes
import com.android.artic.ui.search.data.RecommendWordData
import retrofit2.Call
import retrofit2.mock.Calls

/**
 * object that read, write data from server or local
 * @author greedy0110
 * */
class ArticRepository (

) {
    /**
     * get CategoryList by Asynchronous
     * @author greedy0110
     * @see Category
     * @return Callable Category List
     * */
    fun getCategoryList(): Call<List<Category>> {
        return Calls.response(
            listOf(
                Category(
                    id = 0, name = "category", archive_ids = listOf(1,2,3)
                ),Category(
                    id = 0, name = "category", archive_ids = listOf(1,2,3)
                ),Category(
                    id = 0, name = "category", archive_ids = listOf(1,2,3)
                ),Category(
                    id = 0, name = "category", archive_ids = listOf(1,2,3)
                )
            )
        )
    }

    fun getReadingHistoryArticleList():Call<List<Article>> {
        return Calls.response(
            listOf(
                Article(
                    id = 0,
                    title = "제품 디자이너는 코딩에 대해 잊어야 합니다. ",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/Ixosq.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "댑 (DApp)이 몰고올 디자이너 ‘댑’ ",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/Ixosq.jpg",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "사용자 경험(UX), 망해가던 Gucci를",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/Ixosq.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "디자이너가 알아두면 좋은 인하우스와 에이전시의 차이점",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/Ixosq.jpg",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "와츠앱 스티커 작업기를 가진다면 어떻게 해야할까?",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/Ixosq.jpg",
                    like = 999,
                    isLiked = false
                )
            )
        )
    }

    /**
     * get an article by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticle(articleId: Int): Call<Article> {
        return Calls.response(
            Article(
                id = 0,
                title = "article",
                url = "https://github.com/artic-development/artic_android",
                title_img_url = "https://img.theqoo.net/img/lQZRv.jpg",
                like = 999,
                isLiked = false
            )
        )
    }

    /**
     * get article list which is selected by team artic by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticPickList():Call<List<Article>> {
        return Calls.response(
            listOf(
                Article(
                    id = 0,
                    title = "artic pick article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "artic pick article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "artic pick article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "artic pick article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "artic pick article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = false
                )
            )
        )
    }

    /**
     * get archive name given archive Id by Asynchronous
     * @author greedy0110
     * */
    fun getArchiveName(archiveId: Int): Call<String> {
        return Calls.response(
            "지금 바로 시작할 수 있는 린 브랜딩 방법론을 알려주세요"
        )
    }

    /**
     * get dummy article list by asynchronous
     * @see Article
     * @author greedy0110
     * */
    fun getDummyArticleList(): Call<List<Article>> {
        return Calls.response(
            listOf(
                Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                )
            )
        )
    }

    /**
     * get archive list given category id by async
     * @see Archive
     * @author greedy0110
     * */
    fun getArchiveListGivenCategory(categoryId: Int): Call<List<Archive>>{
        return Calls.response(
            listOf(
                Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 1,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 2,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 3,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 4,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 5,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                )
            )
        )
    }

    /**
     * get new archive list by async
     * @see Archive
     * @author greedy0110
     * */
    fun getNewArchiveList(): Call<List<Archive>> {
        return Calls.response(
            listOf(
                Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    categories = listOf("Design", "Management"),
                    title = "PM과 디자이너가 서로 잘 소통하는 법을 알려주세요",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 1,
                    category_ids = listOf(1,2,3),
                    categories = listOf("Design", "Management"),
                    title = "린 브랜딩을 어떻게 시작해야 할까?",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 2,
                    category_ids = listOf(1,2,3),
                    categories = listOf("Design", "Management"),
                    title = "PM과 디자이너가 서로 잘 소통하는 법을 알려주세요",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 3,
                    category_ids = listOf(1,2,3),
                    categories = listOf("Management"),
                    title = "린 브랜딩을 어떻게 시작해야 할까?",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                )
            )
        )
    }

    /**
     * get new article list by async
     * @see Article
     * @author greedy0110
     * */
    fun getNewArticleList(): Call<List<Article>> {
        return Calls.response(
            listOf(
                Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/XsHRu.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/XsHRu.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/XsHRu.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/XsHRu.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/XsHRu.jpg",
                    like = 999,
                    isLiked = false
                )
            )
        )
    }

    /**
     * get article list given archive id by async
     * @see Article
     * @author greedy0110
     * */
    fun getArticleListGivenArchive(archiveId: Int): Call<List<Article>> {
        return Calls.response(
            listOf(
                Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                )
            )
        )
    }

    // @수민
    fun getMyArchiveList() : Call<List<Archive>> {
        return Calls.response(
            listOf(
                Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                )
            )
        )
    }

    // @수민) 추천 검색어 받아오기
    fun getRecommendWordList() : Call<List<RecommendWordData>> {
        return Calls.response(
            listOf(
//                "UI/UX 디자인", "BX 디자인", "브랜딩", "서비스 기획", "편집 디자인", "안드로이드", "iOS", "서버"
                RecommendWordData(
                    word= "UI/UX 디자인"
                ),
                RecommendWordData(
                    word= "BX 디자인"
                ),
                RecommendWordData(
                    word= "브랜딩"
                ),
                RecommendWordData(
                    word = "서비스 기획"
                ),
                RecommendWordData(
                    word = "편집 디자인"
                ),
                RecommendWordData(
                    word = "안드로이드"
                ),
                RecommendWordData(
                    word = "iOS"
                ),
                RecommendWordData(
                    word = "서버"
                )
            )
        )
    }



    fun getMyPageScrap():Call<List<Archive>>{
        return Calls.response(
            listOf(
                Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)

                ),Archive(
                    id = 1,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 2,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 3,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 4,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 5,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                )


            )
        )
    }


    fun getMyPageMe() : Call<List<Archive>>{
        return Calls.response(
            listOf(
                Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 1,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 2,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 3,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 4,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 5,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                )            )
        )
    }


    // TODO 어떻게 여러가지 타입의 서버에서 받아오는 데이터를 한번에 처리할 수 있을까?
    /**
     * get new notification list by async
     * @see AppNotification
     * @author greedy0110
     * */
    fun getNewNotificationList(): Call<List<AppNotification>> {
        return Calls.response(
            listOf(
                AddArticleNotification(
                    viewType = NotificationType.ADD_ARTICLE,
                    date = Dates.yesterday,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    archive_title = "new notification",
                    archive_id = 0
                ), AddArticleNotification(
                    viewType = NotificationType.ADD_ARTICLE,
                    date = 5.minutes.ago,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    archive_title = "new notification",
                    archive_id = 0
                ),
                RemindArticleNotification(
                    viewType = NotificationType.REMIND_ARCHIVE,
                    date = 6.days.ago,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    articleName = "remind article",
                    num_article = 15
                ), AddArticleNotification(
                    viewType = NotificationType.ADD_ARTICLE,
                    date = 5.days.ago,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    archive_title = "new notification",
                    archive_id = 0
                ), RecommendArchiveNotification (
                    viewType = NotificationType.RECOMMEND_ARCHIVE,
                    date = 5.days.ago,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    archiveId = 0,
                    articleImgUrls = listOf(
                        "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
                    )
                ), RecommendArchiveNotification (
                    viewType = NotificationType.RECOMMEND_ARCHIVE,
                    date = 5.days.ago,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    archiveId = 0,
                    articleImgUrls = listOf(
                        "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                        "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                        "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
                    )
                )
            )
        )
    }

    /**
     * get already read notification list by async
     * @see AppNotification
     * @author greedy0110
     * */
    fun getAlreadyReadNotificationList(): Call<List<AppNotification>> {
        return Calls.response(
            listOf(
                AddArticleNotification(
                    viewType = NotificationType.ADD_ARTICLE,
                    date = Dates.yesterday,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    archive_title = "new notification",
                    archive_id = 0
                ), AddArticleNotification(
                    viewType = NotificationType.ADD_ARTICLE,
                    date = 5.minutes.ago,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    archive_title = "new notification",
                    archive_id = 0
                ),
                RemindArticleNotification(
                    viewType = NotificationType.REMIND_ARCHIVE,
                    date = 6.days.ago,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    articleName = "remind article",
                    num_article = 15
                ), AddArticleNotification(
                    viewType = NotificationType.ADD_ARTICLE,
                    date = 5.days.ago,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    archive_title = "new notification",
                    archive_id = 0
                ), RecommendArchiveNotification (
                    viewType = NotificationType.RECOMMEND_ARCHIVE,
                    date = 5.days.ago,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    archiveId = 0,
                    articleImgUrls = listOf(
                        "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
                    )
                ), RecommendArchiveNotification (
                    viewType = NotificationType.RECOMMEND_ARCHIVE,
                    date = 5.days.ago,
                    img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    archiveId = 0,
                    articleImgUrls = listOf(
                        "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                        "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                        "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
                    )
                )
            )
        )
    }

    fun getSearchArticleList(keyword: String): Call<List<Article>> {
        return Calls.response(
            listOf(
                Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                )
            )
        )
    }

    /**
     * get search archive list given search keyword
     * @see Archive
     * @author greedy0110
     * */
    fun getSearchArchiveList(keyword: String): Call<List<Archive>> {
        return Calls.response(
            listOf(
                Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "search archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "search archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "search archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "search archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "search archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "search archive list",
                    title_img_url = "https://img.theqoo.net/img/GOvlc.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                )
            )
        )
    }
}


