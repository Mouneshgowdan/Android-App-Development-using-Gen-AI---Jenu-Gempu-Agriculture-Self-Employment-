package com.jenugumpu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jenugumpu.data.local.entity.PriceSnapshotEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PriceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrice(snapshot: PriceSnapshotEntity)

    @Query("SELECT * FROM price_snapshots ORDER BY capturedAt DESC LIMIT 1")
    fun observeLatestPrice(): Flow<PriceSnapshotEntity?>
}
