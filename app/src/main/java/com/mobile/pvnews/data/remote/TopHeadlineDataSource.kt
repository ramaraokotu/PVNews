package com.mobile.pvnews.data.remote

import com.mobile.pvnews.data.dto.TopHeadlinesResponse
import com.mobile.pvnews.utils.ErrorMessages
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class TopHeadlineDataSource @Inject constructor(private val topHeadlineApiService: TopHeadlineApiService) {

    suspend fun fetchTopHeadlines(country: String): Result<TopHeadlinesResponse> {
        return try {
            val response = topHeadlineApiService.getTopHeadlinesByCountry(country)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(ErrorMessages.NETWORK_ERROR + ": ${response.message()}"))
            }
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception(ErrorMessages.TIMEOUT_ERROR))
        } catch (e: IOException) {
            Result.failure(Exception(ErrorMessages.NETWORK_ERROR))
        } catch (e: Exception) {
            Result.failure(Exception(ErrorMessages.UNKNOWN_ERROR))
        }
    }
}