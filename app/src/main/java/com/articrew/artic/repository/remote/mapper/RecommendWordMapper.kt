package com.articrew.artic.repository.remote.mapper

import com.articrew.artic.repository.remote.response.RecommendationResponse
import com.articrew.artic.ui.search.data.RecommendWordData

object RecommendWordMapper {
    fun to(res: RecommendationResponse): RecommendWordData {
        return RecommendWordData(res.search_word)
    }
}