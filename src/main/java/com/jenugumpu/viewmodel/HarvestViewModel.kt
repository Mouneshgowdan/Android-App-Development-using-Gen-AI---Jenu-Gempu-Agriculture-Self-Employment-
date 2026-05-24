package com.jenugumpu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jenugumpu.data.local.entity.HarvestEntryEntity
import com.jenugumpu.data.repository.HarvestRepository
import com.jenugumpu.domain.model.FloralSource
import com.jenugumpu.domain.model.HoneyGrade
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HarvestViewModel(
    private val repository: HarvestRepository
) : ViewModel() {
    val harvests = repository.observeHarvests()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalStockKg = repository.observeTotalStockKg()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val stockBySource = repository.observeStockByFloralSource()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addHarvest(
        batchId: String,
        location: String,
        quantityKg: Double,
        floralSource: FloralSource,
        grade: HoneyGrade
    ) {
        viewModelScope.launch {
            repository.addHarvest(
                HarvestEntryEntity(
                    batchId = batchId.trim(),
                    harvestDate = System.currentTimeMillis(),
                    location = location.trim(),
                    quantityKg = quantityKg,
                    floralSource = floralSource.name,
                    moisturePercent = null,
                    colorScore = null,
                    finalGrade = grade.name,
                    notes = null
                )
            )
        }
    }
}

class HarvestViewModelFactory(
    private val repository: HarvestRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HarvestViewModel(repository) as T
    }
}
