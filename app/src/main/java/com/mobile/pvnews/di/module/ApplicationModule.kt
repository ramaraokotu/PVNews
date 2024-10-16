package com.mobile.pvnews.di.module

import android.content.Context

import com.mobile.pvnews.di.BaseUrl
import com.mobile.pvnews.di.NetworkAPIKey
import com.mobile.pvnews.data.remote.ApiKeyInterceptor
import com.mobile.pvnews.data.remote.TopHeadlineApiService
import com.mobile.pvnews.data.remote.TopHeadlineDataSource
import com.mobile.pvnews.data.repository.TopHeadlinesRepositoryImpl
import com.mobile.pvnews.domain.repository.TopHeadlineRepository
import com.mobile.pvnews.domain.usecase.GetTopHeadlineUseCase
import com.mobile.pvnews.utils.AppConstants
import com.mobile.pvnews.utils.DefaultDispatcherProvider
import com.mobile.pvnews.utils.DispatcherProvider
import com.mobile.pvnews.utils.NetworkHelper
import com.mobile.pvnews.utils.NetworkHelperImpl
import com.mobile.pvnews.utils.logger.AppLogger
import com.mobile.pvnews.utils.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): TopHeadlineApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(TopHeadlineApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor):
            OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperImpl(context)
    }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(@NetworkAPIKey apiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(apiKey)


    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = AppConstants.BASE_URL


    @NetworkAPIKey
    @Provides
    fun provideApiKey(): String = AppConstants.API_KEY

    @Provides
    @Singleton
    fun provideTopHeadlinesDataSource(
        apiService: TopHeadlineApiService
    ): TopHeadlineDataSource {
        return TopHeadlineDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideTopHeadlinesRepository(
        topHeadlineDataSource: TopHeadlineDataSource
    ): TopHeadlineRepository {
        return TopHeadlinesRepositoryImpl(topHeadlineDataSource)
    }

    @Provides
    @Singleton
    fun provideGetTopHeadlineUseCase(
        topHeadlineRepository: TopHeadlineRepository
    ): GetTopHeadlineUseCase {
        return GetTopHeadlineUseCase(topHeadlineRepository)
    }
}