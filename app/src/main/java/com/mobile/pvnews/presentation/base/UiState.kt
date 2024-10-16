package com.mobile.pvnews.presentation.base

import com.mobile.pvnews.domain.model.Article

data class UiState(
    val isLoading: Boolean = false,
    val topHeadline: List<Article> = emptyList(),
    val error: String = ""
)