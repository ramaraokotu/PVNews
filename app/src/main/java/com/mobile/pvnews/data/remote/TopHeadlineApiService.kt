package com.mobile.pvnews.data.remote

import com.mobile.pvnews.data.dto.TopHeadlinesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlineApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlinesByCountry(
        @Query("country") country: String
    ): Response<TopHeadlinesResponse>

}