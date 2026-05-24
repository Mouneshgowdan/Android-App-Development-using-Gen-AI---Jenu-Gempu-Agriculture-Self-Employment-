package com.jenugumpu.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "harvest_entries")
data class HarvestEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val batchId: String,
    val harvestDate: Long,
    val location: String,
    val quantityKg: Double,
    val floralSource: String,
    val moisturePercent: Double?,
    val colorScore: Int?,
    val finalGrade: String,
    val notes: String?
)
