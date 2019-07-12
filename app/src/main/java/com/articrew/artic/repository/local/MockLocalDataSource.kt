package com.articrew.artic.repository.local

import com.articrew.artic.data.Archive
import com.articrew.artic.data.Article
import com.articrew.artic.data.Category
import com.articrew.artic.data.MyPage
import com.articrew.artic.data.notification.*
import com.articrew.artic.ui.search.data.RecommendWordData
import khronos.Dates
import khronos.days
import khronos.minutes
import retrofit2.Call
import retrofit2.mock.Calls

class MockLocalDataSource : LocalDataSource {
    override fun getCategoryList(): List<Category> {
        return listOf(
                Category(
                    id = 0, name = "category", archive_ids = listOf(1,2,3)
                ), Category(
                    id = 0, name = "category", archive_ids = listOf(1,2,3)
                ), Category(
                    id = 0, name = "category", archive_ids = listOf(1,2,3)
                ), Category(
                    id = 0, name = "category", archive_ids = listOf(1,2,3)
                )
            )

    }

    override fun getReadingHistoryArticleList(): List<Article> {
        return listOf(
                Article(
                    id = 0,
                    title = "제품 디자이너는 코딩에 대해 잊어야 합니다. ",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/Ixosq.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "댑 (DApp)이 몰고올 디자이너 ‘댑’ ",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/Ixosq.jpg",
                    like = 999,
                    isLiked = true
                ), Article(
                    id = 0,
                    title = "사용자 경험(UX), 망해가던 Gucci를",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/Ixosq.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "디자이너가 알아두면 좋은 인하우스와 에이전시의 차이점",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/Ixosq.jpg",
                    like = 999,
                    isLiked = true
                ), Article(
                    id = 0,
                    title = "와츠앱 스티커 작업기를 가진다면 어떻게 해야할까?",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/Ixosq.jpg",
                    like = 999,
                    isLiked = false
                )
        )
    }

    override fun getArticle(articleId: Int): Article{
        return Article(
                id = 0,
                title = "article",
                url = "https://github.com/artic-development/artic_android",
                title_img_url = "https://img.theqoo.net/img/lQZRv.jpg",
                like = 999,
                isLiked = false
            )

    }

    override fun getArticPickList(): List<Article>{
        return listOf(
                Article(
                    id = 0,
                    title = "artic pick article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "artic pick article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = true
                ), Article(
                    id = 0,
                    title = "artic pick article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "artic pick article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = true
                ), Article(
                    id = 0,
                    title = "artic pick article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = false
                )
        )
    }

    override fun getArchiveName(archiveId: Int): String {
        return "지금 바로 시작할 수 있는 린 브랜딩 방법론을 알려주세요"
    }

    override fun getDummyArticleList(): List<Article> {
        return listOf(
                Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ), Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ), Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                )
            )
    }

    override fun getArchiveListGivenCategory(categoryId: Int): List<Archive> {
        return listOf(
                Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 1,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 2,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 3,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 4,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 5,
                    category_ids = listOf(1,2,3),
                    title = "archive list given category",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                )
            )

    }

    override fun getMyPageInfo(): MyPage {
        return MyPage(name="kyunghee",id="song",profile_img ="",my_info = "")
    }



    override fun getNewArchiveList(): List<Archive> {
        return listOf(
                Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    categories = listOf("Design", "Management"),
                    title = "PM과 디자이너가 서로 잘 소통하는 법을 알려주세요",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 1,
                    category_ids = listOf(1,2,3),
                    categories = listOf("Design", "Management"),
                    title = "린 브랜딩을 어떻게 시작해야 할까?",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 2,
                    category_ids = listOf(1,2,3),
                    categories = listOf("Design", "Management"),
                    title = "PM과 디자이너가 서로 잘 소통하는 법을 알려주세요",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 3,
                    category_ids = listOf(1,2,3),
                    categories = listOf("Management"),
                    title = "린 브랜딩을 어떻게 시작해야 할까?",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                )
            )

    }


    override fun getNewArticleList(): List<Article> {
        return listOf(
                Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/XsHRu.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/XsHRu.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/XsHRu.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/XsHRu.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/XsHRu.jpg",
                    like = 999,
                    isLiked = false
                )
            )
    }

    override fun getArticleListGivenArchive(archiveId: Int): List<Article> {
        return listOf(
                Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ), Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ), Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                )
            )
    }

    override fun getScrapArchiveList(): List<Archive> {
        return listOf(
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
    }

    override fun getMyArchiveList() : List<Archive> {
        return listOf(
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
    }

    override fun getRecommendWordList() : List<RecommendWordData> {
        return listOf(
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
    }

    override fun getMyPageScrap(): List<Archive> {
        return listOf(
                Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)

                ), Archive(
                    id = 1,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 2,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 3,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 4,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 5,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                )
        )
    }


    override fun getMyPageMe() : List<Archive> {
        return listOf(
                Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 1,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 2,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 3,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 4,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ), Archive(
                    id = 5,
                    category_ids = listOf(1,2,3),
                    title = "new archive list",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                )
               )
    }

    override fun getSearchArticleList(keyword: String): List<Article> {
        return listOf(
                Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ), Article(
                    id = 0,
                    title = "로고디자인을 위한 지식에 대한 모든 것을 파헤치다",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                ), Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = true
                ), Article(
                    id = 0,
                    title = "UI / UX 뭐시기 어쩌고",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://img.theqoo.net/img/xRxVm.jpg",
                    like = 999,
                    isLiked = false
                )
            )
    }

    override fun getSearchArchiveList(keyword: String): List<Archive> {
        return listOf(
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
    }
}