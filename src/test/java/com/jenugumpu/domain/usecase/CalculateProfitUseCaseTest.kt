package com.jenugumpu.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculateProfitUseCaseTest {
    private val useCase = CalculateProfitUseCase()

    @Test
    fun calculatesProfitAfterCosts() {
        val summary = useCase.calculate(
            quantityKg = 10.0,
            pricePerKg = 350.0,
            travelCost = 200.0,
            containerCost = 100.0,
            laborCost = 500.0,
            otherCost = 0.0
        )

        assertEquals(3500.0, summary.revenue, 0.01)
        assertEquals(800.0, summary.totalCost, 0.01)
        assertEquals(2700.0, summary.profit, 0.01)
    }
}
