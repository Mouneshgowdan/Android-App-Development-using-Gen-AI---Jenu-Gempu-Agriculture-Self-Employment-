package com.jenugumpu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jenugumpu.data.repository.PriceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PriceViewModel(
    private val repository: PriceRepository
) : ViewModel() {
    val latestPrice = repository.observeLatestPrice()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun refreshPrices() {
        viewModelScope.launch {
            repository.refreshSimulatedPrices()
        }
    }
}

class PriceViewModelFactory(
    private val repository: PriceRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PriceViewModel(repository) as T
    }
}
