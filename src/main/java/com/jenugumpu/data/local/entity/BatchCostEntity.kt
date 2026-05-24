package com.jenugumpu.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "batch_costs",
    indices = [Index("batchId")]
)
data class BatchCostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val batchId: String,
    val travelCost: Double,
    val containerCost: Double,
    val laborCost: Double,
    val otherCost: Double
)
