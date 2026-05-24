package com.jenugumpu.domain.usecase

import com.jenugumpu.data.local.entity.HarvestEntryEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculateStockUseCaseTest {
    private val useCase = CalculateStockUseCase()

    @Test
    fun sumsHarvestQuantity() {
        val entries = listOf(
            harvest(quantityKg = 4.5),
            harvest(quantityKg = 5.5)
        )

        assertEquals(10.0, useCase.totalKg(entries), 0.01)
    }

    private fun harvest(quantityKg: Double): HarvestEntryEntity {
        return HarvestEntryEntity(
            batchId = "B1",
            harvestDate = 0L,
            location = "Village",
            quantityKg = quantityKg,
            floralSource = "WILDFLOWER",
            moisturePercent = null,
            colorScore = null,
            finalGrade = "B",
            notes = null
        )
    }
}
