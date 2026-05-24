package com.jenugumpu.data.repository

import com.jenugumpu.data.local.dao.PriceDao
import com.jenugumpu.data.local.entity.PriceSnapshotEntity
import kotlinx.coroutines.flow.Flow

class PriceRepository(
    private val dao: PriceDao
) {
    fun observeLatestPrice(): Flow<PriceSnapshotEntity?> = dao.observeLatestPrice()

    suspend fun refreshSimulatedPrices() {
        val retail = listOf(320.0, 350.0, 380.0, 420.0).random()
        val wholesale = retail - listOf(60.0, 80.0, 100.0).random()
        dao.insertPrice(
            PriceSnapshotEntity(
                sourceName = "Simulated Market",
                retailPricePerKg = retail,
                wholesalePricePerKg = wholesale,
                capturedAt = System.currentTimeMillis()
            )
        )
    }
}
