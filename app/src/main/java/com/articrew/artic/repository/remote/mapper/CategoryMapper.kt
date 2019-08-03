package com.articrew.artic.repository.remote.mapper

import com.articrew.artic.data.Category
import com.articrew.artic.repository.remote.response.CategoryResponse

object CategoryMapper {
    fun to(res: CategoryResponse): Category {
        return Category(
            id = res.category_idx,
            name = res.category_title
        )
    }
}