package com.articrew.artic.repository.remote.mapper

import com.articrew.artic.data.Article
import com.articrew.artic.repository.remote.response.ArticleResponse

object ArticleMapper {
    fun to(res: ArticleResponse): Article {
        return Article(
            id = res.article_idx,
            like = res.like_cnt?:0,
            title_img_url = res.thumnail,
            title = res.article_title,
            url = res.link,
            isLiked = res.like,
            domain_url = res.domain?:"",
            archive_id = res.archive_idx,
            archive_title = res.archive_title
        )
    }
}