package com.articrew.artic.repository.remote.mapper

import com.articrew.artic.data.Archive
import com.articrew.artic.repository.remote.response.ArchiveResponse

object ArchiveMapper {
    fun to(res: ArchiveResponse): Archive {
        return Archive(
            id = res.archive_idx,
            categories = res.category_all?.map { cate -> cate.category_title },
            category_ids = listOf(res.category_idx),
            category_idx = res.category_idx,
            title = res.archive_title,
            title_img_url = res.archive_img,
            num_article = res.article_cnt?:0,
            scrap = res.scrap
        )
    }
}