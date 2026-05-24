package com.jenugumpu.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jenugumpu.data.local.entity.HarvestEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HarvestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHarvest(entry: HarvestEntryEntity)

    @Update
    suspend fun updateHarvest(entry: HarvestEntryEntity)

    @Delete
    suspend fun deleteHarvest(entry: HarvestEntryEntity)

    @Query("SELECT * FROM harvest_entries ORDER BY harvestDate DESC")
    fun observeHarvests(): Flow<List<HarvestEntryEntity>>

    @Query("SELECT * FROM harvest_entries WHERE batchId = :batchId LIMIT 1")
    fun observeBatch(batchId: String): Flow<HarvestEntryEntity?>

    @Query("SELECT SUM(quantityKg) FROM harvest_entries")
    fun observeTotalStockKg(): Flow<Double?>

    @Query("SELECT floralSource, SUM(quantityKg) AS totalKg FROM harvest_entries GROUP BY floralSource")
    fun observeStockByFloralSource(): Flow<List<StockBySource>>
}

data class StockBySource(
    val floralSource: String,
    val totalKg: Double
)
