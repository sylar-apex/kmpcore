package com.psbapp.mvi

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    // Handle loading state
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    // Toast / success messaging lives in ui/toastbar:
    // showErrorMsgBar(...) / showSuccessMsgBar(...) from a CoroutineScope.

    // Start loading
    fun startLoading() {
        viewModelScope.launch(Dispatchers.Main) {
            _loading.value = true
        }
    }

    // Stop loading
    fun stopLoading() {
        viewModelScope.launch(Dispatchers.Main) {
            _loading.value = false
        }
    }

    fun combineLoading(vararg l: Flow<Boolean>) = viewModelScope.launch {
        combine(l.asList()) { loadings ->
            // Any loading is true
            loadings.any { it }
        }.collect {
            if (it) startLoading() else stopLoading()
        }
    }

    fun <T> Flow<T>.stateInEagerly(defaultValue: T) =
            stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                defaultValue
            )
}