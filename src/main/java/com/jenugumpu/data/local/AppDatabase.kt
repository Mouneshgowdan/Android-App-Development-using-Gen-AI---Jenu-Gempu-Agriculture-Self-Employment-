package com.jenugumpu.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jenugumpu.data.local.dao.HarvestDao
import com.jenugumpu.data.local.dao.PriceDao
import com.jenugumpu.data.local.entity.BatchCostEntity
import com.jenugumpu.data.local.entity.HarvestEntryEntity
import com.jenugumpu.data.local.entity.PriceSnapshotEntity

@Database(
    entities = [
        HarvestEntryEntity::class,
        BatchCostEntity::class,
        PriceSnapshotEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun harvestDao(): HarvestDao
    abstract fun priceDao(): PriceDao
}
