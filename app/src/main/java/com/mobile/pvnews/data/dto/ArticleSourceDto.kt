package com.mobile.pvnews.data.dto

import com.google.gson.annotations.SerializedName
import com.mobile.pvnews.domain.model.ArticleSource

data class ArticleSourceDto(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = ""
)

fun ArticleSourceDto.toArticleSource(): ArticleSource {
    return ArticleSource(
        id = this.id,
        name = this.name
    )
}

