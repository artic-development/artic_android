package com.android.artic.repository

import com.android.artic.ui.category.data.ArchiveCategory
import com.android.artic.ui.category.data.Category
import com.android.artic.ui.home.new_archive.data.ArchiveCardData
import io.reactivex.Observable
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
    fun getNewArchiveList(): Call<List<ArchiveCardData>> {
        return Calls.response(
            listOf(
                ArchiveCardData(
                    archive_id = 0, category = "Animal", title = "고양이는 왜 이렇게 귀여운가. 고양이 키우고싶다. 숙소에 고양이 대려와.", img_url = "http://newsimg.hankookilbo.com/2016/04/13/201604131460701467_1.jpg"
                ),ArchiveCardData(
                    archive_id = 1, category = "Animal", title = "고양이는 왜 이렇게 귀여운가. 고양이 키우고싶다. 숙소에 고양이 대려와.", img_url = "http://newsimg.hankookilbo.com/2016/04/13/201604131460701467_1.jpg"
                ),ArchiveCardData(
                    archive_id = 2, category = "Animal", title = "고양이는 왜 이렇게 귀여운가. 고양이 키우고싶다. 숙소에 고양이 대려와.", img_url = "http://newsimg.hankookilbo.com/2016/04/13/201604131460701467_1.jpg"
                ),ArchiveCardData(
                    archive_id = 3, category = "Animal", title = "고양이는 왜 이렇게 귀여운가. 고양이 키우고싶다. 숙소에 고양이 대려와.", img_url = "http://newsimg.hankookilbo.com/2016/04/13/201604131460701467_1.jpg"
                ),ArchiveCardData(
                    archive_id = 4, category = "Animal", title = "고양이는 왜 이렇게 귀여운가. 고양이 키우고싶다. 숙소에 고양이 대려와.", img_url = "http://newsimg.hankookilbo.com/2016/04/13/201604131460701467_1.jpg"
                )
            )
        )
    }
}