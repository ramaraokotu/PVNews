package com.mobile.pvnews.data.dto

import com.google.gson.annotations.SerializedName
import com.mobile.pvnews.domain.model.Article

data class ArticleDto(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val imageUrl: String = "",
    @SerializedName("source")
    val articleSource: ArticleSourceDto
)

fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        description = description ?: "",
        url = url,
        imageUrl = imageUrl ?: "",
        articleSource = articleSource.toArticleSource()
    )
}

