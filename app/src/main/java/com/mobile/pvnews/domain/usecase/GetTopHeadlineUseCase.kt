package com.mobile.pvnews.domain.usecase

import com.mobile.pvnews.domain.model.Article
import com.mobile.pvnews.domain.repository.TopHeadlineRepository
import com.mobile.pvnews.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetTopHeadlineUseCase @Inject constructor(
    private val repository: TopHeadlineRepository
) {

    suspend operator fun invoke(country: String): Resource<List<Article>> {
        return repository.getTopHeadlines(country)
    }
}
