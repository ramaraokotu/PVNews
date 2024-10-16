package com.mobile.pvnews.domain.repository

import com.mobile.pvnews.domain.model.Article
import com.mobile.pvnews.utils.Resource

interface TopHeadlineRepository {
    suspend fun getTopHeadlines(country: String): Resource<List<Article>>
}