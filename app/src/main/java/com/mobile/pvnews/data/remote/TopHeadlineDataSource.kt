package com.mobile.pvnews.data.remote

import com.mobile.pvnews.data.dto.ArticleDto
import com.mobile.pvnews.data.dto.TopHeadlinesResponse
import com.mobile.pvnews.utils.ErrorMessages
import com.mobile.pvnews.utils.Resource
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class TopHeadlineDataSource @Inject constructor(
    private val topHeadlineApiService: TopHeadlineApiService
) {

    suspend fun fetchTopHeadlines(country: String): Resource<List<ArticleDto>> {
        return try {
            val response = topHeadlineApiService.getTopHeadlinesByCountry(country)
            if (response.isSuccessful && response.body() != null) {
                val articles = response.body()?.articles ?: emptyList()
                if (articles.isNotEmpty()) {
                    Resource.Success(articles)
                } else {
                    Resource.Error(ErrorMessages.DATA_NOT_FOUND)
                }
            } else {
                Resource.Error(ErrorMessages.NETWORK_ERROR + ": ${response.message()}")
            }
        } catch (e: SocketTimeoutException) {
            Resource.Error(ErrorMessages.TIMEOUT_ERROR)
        } catch (e: IOException) {
            Resource.Error(ErrorMessages.NETWORK_ERROR)
        } catch (e: Exception) {
            Resource.Error(ErrorMessages.UNKNOWN_ERROR)
        }
    }
}