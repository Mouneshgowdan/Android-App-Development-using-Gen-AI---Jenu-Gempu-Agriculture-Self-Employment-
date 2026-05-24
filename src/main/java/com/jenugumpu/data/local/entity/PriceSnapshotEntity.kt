package com.jenugumpu.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_snapshots")
data class PriceSnapshotEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sourceName: String,
    val retailPricePerKg: Double,
    val wholesalePricePerKg: Double,
    val capturedAt: Long
)
