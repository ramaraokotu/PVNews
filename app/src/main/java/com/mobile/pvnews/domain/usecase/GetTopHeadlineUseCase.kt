package com.mobile.pvnews.domain.usecase

import com.mobile.pvnews.data.dto.TopHeadlinesResponse
import com.mobile.pvnews.domain.repository.TopHeadlineRepository
import javax.inject.Inject

class GetTopHeadlineUseCase @Inject constructor(
    private val repository: TopHeadlineRepository
) {

    suspend operator fun invoke(country: String): Result<TopHeadlinesResponse> {
        return repository.getTopHeadlines(country)
    }
}