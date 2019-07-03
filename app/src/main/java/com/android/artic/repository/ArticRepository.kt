package com.android.artic.repository

import com.android.artic.data.Archive
import com.android.artic.data.Article
import com.android.artic.ui.category.data.ArchiveCategory
import com.android.artic.ui.category.data.Category
import com.android.artic.ui.home.artic_pick.data.ArticPickData
import com.android.artic.ui.home.new_article_link.data.NewArticleLinkData
import com.android.artic.ui.home.reading_history.data.HistoryData
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
                    en_name = "Design", kr_name = "디자인", children = listOf(
                        ArchiveCategory(1, "UX/UI"),
                        ArchiveCategory(2, "BX"),
                        ArchiveCategory(3, "그래픽"),
                        ArchiveCategory(4, "영상/모션")
                    )
                ),
                Category(
                    en_name = "Plan", kr_name = "서비스 기획", children = listOf(
                        ArchiveCategory(1, "UX/UI"),
                        ArchiveCategory(2, "BX"),
                        ArchiveCategory(3, "그래픽"),
                        ArchiveCategory(4, "영상/모션")
                    )
                ),
                Category(
                    en_name = "Management", kr_name = "매니지먼트", children = listOf(
                        ArchiveCategory(1, "UX/UI"),
                        ArchiveCategory(2, "BX"),
                        ArchiveCategory(3, "그래픽"),
                        ArchiveCategory(4, "영상/모션")
                    )
                )
            )
        )
    }

    /**
     * get New Archive List by Asynchronous
     * @author greedy0110
     * */
    fun getNewArchiveList(): Call<List<Archive>> {
        return Calls.response(
            listOf(
                Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "new archive", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = false
                ),Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "new archive", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = true
                ),Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "new archive", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = false
                ),Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "new archive", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = false
                ),Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "new archive", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = false
                ),Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "new archive", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = false
                )
            )
        )
    }

    /**
     * get archive list given category by asynchronous
     * @author greedy0110
     * */
    fun getArchiveListGivenCategory(categoryId: Int): Call<List<Archive>> {
        return Calls.response(
            listOf(
                Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "archive given category", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = false
                ),Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "archive given category", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = true
                ),Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "archive given category", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = false
                ),Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "archive given category", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = false
                ),Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "archive given category", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = false
                ),Archive(
                    id = 0, category_ids = listOf(1,2,3), title = "archive given category", title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    num_article = 3, categories = listOf("UI/UX", "우왕"), isScarped = false
                )
            )
        )
    }

    fun getReadingHistoryArchiveList():Call<List<HistoryData>> {
        return Calls.response(
            listOf(

               HistoryData(0,"brunch.co.kr","제품 디자이너는 코딩에 대해 잊어야 합니다.","http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"),
                HistoryData(1,"brunch.co.kr","댑(DApp)이 몰고올 디자이너 '댑'","http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"),
                HistoryData(2,"brunch.co.kr","사용자 경헙(UX), 망해가던 Gucci를","http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png"),
                HistoryData(3,"brunch.co.kr","디자이너가 알아두면 좋은 인하우스와 에이전시의 차이점","http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png")

            )

        )
    }

    fun getNewArticleLinkList():Call<List<NewArticleLinkData>> {
        return Calls.response(
            listOf(
                NewArticleLinkData(0,"brunch.co.kr","디자이너가 알아두면 좋은 인하우스와 에이전시는 에이전시의 차이점","https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
                NewArticleLinkData(1,"brunch.co.kr","디자이너가 알아두면 좋은 인하우스와 에이전시는 에이전시의 차이점","https://images.pexels.com/photos/459225/pexels-photo-459225.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
                NewArticleLinkData(2,"brunch.co.kr","디자이너가 알아두면 좋은 인하우스와 에이전시는 에이전시의 차이점","https://images.pexels.com/photos/34950/pexels-photo.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
                NewArticleLinkData(3,"brunch.co.kr","디자이너가 알아두면 좋은 인하우스와 에이전시는 에이전시의 차이점","https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
                NewArticleLinkData(4,"brunch.co.kr","디자이너가 알아두면 좋은 인하우스와 에이전시는 에이전시의 차이점","https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")

            )
        )
    }

    fun getArticPickList():Call<List<ArticPickData>> {
        return Calls.response(
            listOf(
                ArticPickData(0,"brunch.co.kr","디자이너가 알아두면 좋은 인하우스와 에이전시는 에이전시의 차이점","https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
                ArticPickData(1,"brunch.co.kr","디자이너가 알아두면 좋은 인하우스와 에이전시는 에이전시의 차이점","https://images.pexels.com/photos/459225/pexels-photo-459225.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
                ArticPickData(2,"brunch.co.kr","디자이너가 알아두면 좋은 인하우스와 에이전시는 에이전시의 차이점","https://images.pexels.com/photos/34950/pexels-photo.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
                ArticPickData(3,"brunch.co.kr","디자이너가 알아두면 좋은 인하우스와 에이전시는 에이전시의 차이점","https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"),
                ArticPickData(4,"brunch.co.kr","디자이너가 알아두면 좋은 인하우스와 에이전시는 에이전시의 차이점","https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")

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
     * get dummy category list by asynchronous
     * @see Category
     * @author greedy0110
     * */
    fun getDummyCategoryList(): Call<List<com.android.artic.data.Category>> {
        return Calls.response(
            listOf(
                com.android.artic.data.Category(
                    id = 0,
                    name = "design"
                ),com.android.artic.data.Category(
                    id = 0,
                    name = "design"
                ),com.android.artic.data.Category(
                    id = 0,
                    name = "design"
                ),com.android.artic.data.Category(
                    id = 0,
                    name = "design"
                ),com.android.artic.data.Category(
                    id = 0,
                    name = "design"
                ),com.android.artic.data.Category(
                    id = 0,
                    name = "design"
                ),com.android.artic.data.Category(
                    id = 0,
                    name = "design"
                ),com.android.artic.data.Category(
                    id = 0,
                    name = "design"
                )
            )
        )
    }

    /**
     * get dummy archive list by asynchronous
     * @see Archive
     * @author greedy0110
     * */
    fun getDummyArchiveList(): Call<List<Archive>> {
        return Calls.response(
            listOf(
                Archive(
                    id = 0,
                    category_ids = listOf(1,2,3),
                    title = "dummy archive",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 1,
                    category_ids = listOf(1,2,3),
                    title = "dummy archive",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 2,
                    category_ids = listOf(1,2,3),
                    title = "dummy archive",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 3,
                    category_ids = listOf(1,2,3),
                    title = "dummy archive",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 4,
                    category_ids = listOf(1,2,3),
                    title = "dummy archive",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                ),Archive(
                    id = 5,
                    category_ids = listOf(1,2,3),
                    title = "dummy archive",
                    title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                    num_article = 3,
                    article_ids = listOf(1,2,3)
                )
            )
        )
    }

    /**
     * get dummy archive by asynchronous
     * @see Archive
     * @author greedy0110
     * */
    fun getDummyArchive(): Call<Archive> {
        return Calls.response(
            Archive(
                id = 0,
                category_ids = listOf(1,2,3),
                title = "dummy archive",
                title_img_url = "https://pixel.nymag.com/imgs/daily/vulture/2019/03/13/13-captain-marvel-flerken-cat.w700.h700.jpg",
                num_article = 3,
                article_ids = listOf(1,2,3)
            )
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
                    title = "dummy article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "dummy article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "dummy article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "dummy article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "dummy article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = false
                )
            )
        )
    }

    /**
     * get an dummy article by asynchronous
     * @see Article
     * @author greedy0110
     * */
    fun getDummyArticle(): Call<Article> {
        return Calls.response(
            Article(
                id = 0,
                title = "dummy article",
                url = "https://github.com/artic-development/artic_android",
                title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                like = 999,
                isLiked = false
            )
        )
    }
}


