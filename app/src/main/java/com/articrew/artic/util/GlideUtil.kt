package com.articrew.artic.util

import com.articrew.artic.R
import com.bumptech.glide.request.RequestOptions

// 기본으로 보여지는 이미지들 관리하자.
val defaultHolderOptions = RequestOptions()
    .placeholder(R.drawable.empty_thumbnail)
    .fallback(R.drawable.empty_thumbnail)
    .error(R.drawable.empty_thumbnail)