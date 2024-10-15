package com.mobile.pvnews.data.repository

import com.mobile.pvnews.data.dto.TopHeadlinesResponse
import com.mobile.pvnews.data.remote.TopHeadlineDataSource
import com.mobile.pvnews.domain.repository.TopHeadlineRepository
import javax.inject.Inject

class TopHeadlinesRepositoryImpl @Inject constructor(
    private val remoteDataSource: TopHeadlineDataSource
) : TopHeadlineRepository {

    override suspend fun getTopHeadlines(country: String): Result<TopHeadlinesResponse> {
        return remoteDataSource.fetchTopHeadlines(country)
    }
}