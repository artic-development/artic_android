package com.android.artic.ui.new_article.data

data class NewArticleData (
    var title : String,
    var article : NewArticleData,
    var article_list : ArrayList<NewArticleData>
)