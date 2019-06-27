package com.android.artic.repository

import com.android.artic.ui.category.data.ArchiveCategory
import com.android.artic.ui.category.data.Category
import com.android.artic.ui.home.data.ArchiveHomeCard
import com.android.artic.ui.home.data.HomeCard
import com.android.artic.ui.home.data.HomeCardKind
import com.android.artic.ui.home.data.LinkHomeCard
import io.reactivex.Observable
import java.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.mock.Calls

/**
 * object that read, write data from server or local
 * @author greedy0110
 * */
class ArticRepository (

) {
    /**
     * get HomeCard List by Asynchronous
     * @author greedy0110
     * @see HomeCard
     * @see ArchiveHomeCard
     * @see LinkHomeCard
     * @return Observable HomeCard List
     * */
    fun getHomeCardListObservable(): Observable<List<HomeCard>> {
        return Observable.just(
            listOf(
                ArchiveHomeCard(
                    viewType = HomeCardKind.ARCHIVE,
                    archive_id = 0,
                    title = "린 브랜딩을 어떻게\n시작해야 할까?",
                    desc = "초보자를 위한 린 브랜딩 모음",
                    img_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/180717_%EC%97%B4%EB%A6%B0%EC%9D%8C%EC%95%85%ED%9A%8C_%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4_01.jpg/220px-180717_%EC%97%B4%EB%A6%B0%EC%9D%8C%EC%95%85%ED%9A%8C_%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4_01.jpg"
                ),
                LinkHomeCard(
                    viewType = HomeCardKind.LINK,
                    link_url = "",
                    curator = "김가형",
                    title = "지금 바로 시작할 수 있는\n린 브랜드 방법론",
                    img_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/180717_%EC%97%B4%EB%A6%B0%EC%9D%8C%EC%95%85%ED%9A%8C_%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4_01.jpg/220px-180717_%EC%97%B4%EB%A6%B0%EC%9D%8C%EC%95%85%ED%9A%8C_%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4_01.jpg"
                )
            )
        )
    }

    /**
     * get HomeCard List by Asynchronous
     * @author greedy0110
     * @see HomeCard
     * @see ArchiveHomeCard
     * @see LinkHomeCard
     * @return Callable HomeCard List
     * */
    fun getHomeCardList(): Call<List<HomeCard>> {
        return Calls.response(listOf(
            ArchiveHomeCard(
                viewType = HomeCardKind.ARCHIVE,
                archive_id = 0,
                title = "린 브랜딩을 어떻게\n시작해야 할까?",
                desc = "초보자를 위한 린 브랜딩 모음",
                img_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/180717_%EC%97%B4%EB%A6%B0%EC%9D%8C%EC%95%85%ED%9A%8C_%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4_01.jpg/220px-180717_%EC%97%B4%EB%A6%B0%EC%9D%8C%EC%95%85%ED%9A%8C_%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4_01.jpg"
            ),
            LinkHomeCard(
                viewType = HomeCardKind.LINK,
                link_url = "",
                curator = "김가형",
                title = "지금 바로 시작할 수 있는\n린 브랜드 방법론",
                img_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/180717_%EC%97%B4%EB%A6%B0%EC%9D%8C%EC%95%85%ED%9A%8C_%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4_01.jpg/220px-180717_%EC%97%B4%EB%A6%B0%EC%9D%8C%EC%95%85%ED%9A%8C_%ED%8A%B8%EC%99%80%EC%9D%B4%EC%8A%A4_01.jpg"
            )
        ))
    }

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
}