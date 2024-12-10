package com.example.cryptocurrencyviewer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyviewer.domain.CryptoItem
import com.example.cryptocurrencyviewer.domain.usecases.GetCryptoPricesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCryptoPricesUseCase: GetCryptoPricesUseCase
) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Loading)
    val state: LiveData<State> = _state

    private val _isRefreshing = MutableLiveData<Boolean>(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    fun setRefreshing(refreshing: Boolean) {
        _isRefreshing.value = refreshing
    }

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = State.Loading
            getCryptoPricesUseCase().fold(
                onSuccess = { data ->
                    _state.value = State.Success(data)
                },
                onFailure = { exception ->
                    _state.value = State.Error(exception.message ?: "Unknown error")
                }
            )
        }
    }

    fun refreshData() {
        loadData()
    }


    sealed class State {
        data object Loading : State()
        data class Success(val data: List<CryptoItem>) : State()
        data class Error(val message: String) : State()
    }
}
