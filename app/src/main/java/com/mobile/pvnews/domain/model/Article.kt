package com.mobile.pvnews.domain.model

data class Article(
    val title: String = "",
    val description: String?,
    val url: String = "",
    val imageUrl: String = "",
    val articleSource: ArticleSource
)
