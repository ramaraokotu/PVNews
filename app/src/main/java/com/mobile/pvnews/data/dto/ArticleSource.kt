package com.mobile.pvnews.data.dto

import com.google.gson.annotations.SerializedName

data class ArticleSource(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = ""
)
