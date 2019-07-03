package com.android.artic.repository

import com.android.artic.data.Archive
import com.android.artic.data.Article
import com.android.artic.ui.category.data.ArchiveCategory
import com.android.artic.ui.category.data.Category
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

    fun getReadingHistoryArticleList():Call<List<Article>> {
        return Calls.response(
            listOf(
                Article(
                    id = 0,
                    title = "history article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "history article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "history article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "history article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "history article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    like = 999,
                    isLiked = false
                )
            )
        )
    }



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
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "new article",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
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
                    title = "article given archive",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "article given archive",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "article given archive",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = false
                ),Article(
                    id = 0,
                    title = "article given archive",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = true
                ),Article(
                    id = 0,
                    title = "article given archive",
                    url = "https://github.com/artic-development/artic_android",
                    title_img_url = "https://avatars0.githubusercontent.com/u/52156026?s=200&v=4",
                    like = 999,
                    isLiked = false
                )
            )
        )
    }
}


