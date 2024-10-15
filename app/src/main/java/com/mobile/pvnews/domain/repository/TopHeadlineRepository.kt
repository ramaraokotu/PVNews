package com.mobile.pvnews.domain.repository

import com.mobile.pvnews.data.dto.TopHeadlinesResponse

interface TopHeadlineRepository {

    suspend fun getTopHeadlines(country: String): Result<TopHeadlinesResponse>
}