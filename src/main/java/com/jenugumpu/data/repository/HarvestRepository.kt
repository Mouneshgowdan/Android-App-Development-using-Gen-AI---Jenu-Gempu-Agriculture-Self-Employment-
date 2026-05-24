package com.jenugumpu.data.repository

import com.jenugumpu.data.local.dao.HarvestDao
import com.jenugumpu.data.local.dao.StockBySource
import com.jenugumpu.data.local.entity.HarvestEntryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HarvestRepository(
    private val dao: HarvestDao
) {
    fun observeHarvests(): Flow<List<HarvestEntryEntity>> = dao.observeHarvests()

    fun observeTotalStockKg(): Flow<Double> =
        dao.observeTotalStockKg().map { it ?: 0.0 }

    fun observeStockByFloralSource(): Flow<List<StockBySource>> =
        dao.observeStockByFloralSource()

    suspend fun addHarvest(entry: HarvestEntryEntity) {
        dao.insertHarvest(entry)
    }
}
