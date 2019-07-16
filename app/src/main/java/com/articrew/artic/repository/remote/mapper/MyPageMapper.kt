package com.articrew.artic.repository.remote.mapper

import com.articrew.artic.data.MyPage
import com.articrew.artic.repository.remote.response.MyPageResponse

object MyPageMapper {
    fun to(res: MyPageResponse): MyPage {
        return MyPage(
            name = res.userName,
            id = res.userId?:"",
            profile_img = res.userImg,
            my_info = res.userIntro
        )
    }
}