package com.jenugumpu.domain.usecase

import com.jenugumpu.domain.model.ProfitSummary

class CalculateProfitUseCase {
    fun calculate(
        quantityKg: Double,
        pricePerKg: Double,
        travelCost: Double,
        containerCost: Double,
        laborCost: Double,
        otherCost: Double
    ): ProfitSummary {
        val revenue = quantityKg * pricePerKg
        val totalCost = travelCost + containerCost + laborCost + otherCost
        return ProfitSummary(
            revenue = revenue,
            totalCost = totalCost,
            profit = revenue - totalCost
        )
    }
}
