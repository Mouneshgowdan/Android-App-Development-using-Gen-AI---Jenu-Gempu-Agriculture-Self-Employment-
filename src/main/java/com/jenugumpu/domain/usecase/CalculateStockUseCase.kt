package com.jenugumpu.domain.usecase

import com.jenugumpu.data.local.entity.HarvestEntryEntity

class CalculateStockUseCase {
    fun totalKg(entries: List<HarvestEntryEntity>): Double {
        return entries.sumOf { it.quantityKg }
    }
}
