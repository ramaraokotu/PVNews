package com.mobile.pvnews.data.repository

import com.mobile.pvnews.data.dto.toArticle
import com.mobile.pvnews.data.remote.TopHeadlineDataSource
import com.mobile.pvnews.domain.model.Article
import com.mobile.pvnews.domain.repository.TopHeadlineRepository
import com.mobile.pvnews.utils.Resource
import javax.inject.Inject

class TopHeadlinesRepositoryImpl @Inject constructor(
    private val remoteDataSource: TopHeadlineDataSource
) : TopHeadlineRepository {

    override suspend fun getTopHeadlines(country: String): Resource<List<Article>> {
        return when (val response = remoteDataSource.fetchTopHeadlines(country)) {
            is Resource.Success -> {
                val articles = response.data?.map { it.toArticle() } ?: emptyList()
                Resource.Success(articles)
            }

            is Resource.Error -> {
                Resource.Error(response.message ?: "An unexpected error occurred")
            }

            is Resource.Loading -> {
                Resource.Loading()
            }
        }
    }
}