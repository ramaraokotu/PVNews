package com.mobile.pvnews.utils

sealed class NetworkException(message: String) : Exception(message) {
    class TimeoutException : NetworkException("Timeout Error")
    class NetworkErrorException : NetworkException("Network Error")
    class EmptyBodyException : NetworkException("Empty Response Body")
    class UnknownException(cause: Throwable) : NetworkException(cause.message ?: "Unknown Error")
}