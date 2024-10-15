package com.mobile.pvnews.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkAPIKey

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrl