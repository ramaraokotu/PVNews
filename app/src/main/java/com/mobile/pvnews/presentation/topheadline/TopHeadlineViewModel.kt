package com.mobile.pvnews.presentation.topheadline

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.pvnews.domain.usecase.GetTopHeadlineUseCase
import com.mobile.pvnews.presentation.base.UiState
import com.mobile.pvnews.utils.AppConstants
import com.mobile.pvnews.utils.DispatcherProvider
import com.mobile.pvnews.utils.ErrorMessages
import com.mobile.pvnews.utils.NetworkHelper
import com.mobile.pvnews.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val topHeadlineUseCase: GetTopHeadlineUseCase,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    private val _topHeadlineUiState = mutableStateOf(UiState())

    val topHeadlineUiState: State<UiState> = _topHeadlineUiState

    private fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    init {
        startFetchingArticles()
    }

    private fun startFetchingArticles() {
        if (checkInternetConnection()) {
            fetchArticles()
        } else {
            _topHeadlineUiState.value = UiState(error = ErrorMessages.NETWORK_ERROR)
        }
    }

    private fun fetchArticles() {
        viewModelScope.launch(dispatcherProvider.io) {
            _topHeadlineUiState.value = UiState(isLoading = true)

            when (val result = topHeadlineUseCase.invoke(AppConstants.COUNTRY)) {
                is Resource.Success -> {
                    val articles = result.data ?: emptyList()
                    withContext(dispatcherProvider.main) {
                        _topHeadlineUiState.value = UiState(topHeadline = articles)
                    }
                }

                is Resource.Error -> {
                    withContext(dispatcherProvider.main) {
                        _topHeadlineUiState.value =
                            UiState(error = result.message ?: ErrorMessages.UNKNOWN_ERROR)
                    }
                }

                is Resource.Loading -> {
                    _topHeadlineUiState.value = UiState(isLoading = true)
                }
            }
        }
    }
}