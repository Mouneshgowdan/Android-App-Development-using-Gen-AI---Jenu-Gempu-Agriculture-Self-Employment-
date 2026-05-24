package com.jenugumpu.viewmodel

import androidx.lifecycle.ViewModel
import com.jenugumpu.domain.model.ProfitSummary
import com.jenugumpu.domain.usecase.CalculateProfitUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfitViewModel(
    private val calculateProfitUseCase: CalculateProfitUseCase = CalculateProfitUseCase()
) : ViewModel() {
    private val _summary = MutableStateFlow(ProfitSummary(0.0, 0.0, 0.0))
    val summary: StateFlow<ProfitSummary> = _summary.asStateFlow()

    fun calculate(
        quantityKg: Double,
        pricePerKg: Double,
        travelCost: Double,
        containerCost: Double,
        laborCost: Double,
        otherCost: Double
    ) {
        _summary.value = calculateProfitUseCase.calculate(
            quantityKg = quantityKg,
            pricePerKg = pricePerKg,
            travelCost = travelCost,
            containerCost = containerCost,
            laborCost = laborCost,
            otherCost = otherCost
        )
    }
}
