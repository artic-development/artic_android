package com.android.artic.repository

import com.android.artic.ui.home.data.ArchiveHomeCard
import com.android.artic.ui.home.data.HomeCard
import com.android.artic.ui.home.data.HomeCardKind
import com.android.artic.ui.home.data.LinkHomeCard
import io.reactivex.Observable

class ArticRepository (

) {
    fun getHomeCardList(): Observable<List<HomeCard>> {
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
}