package com.jenugumpu.domain.usecase

import com.jenugumpu.domain.model.HoneyGrade

class SimulateGradeUseCase {
    fun gradeHoney(moisturePercent: Double, colorScore: Int): HoneyGrade {
        return when {
            moisturePercent <= 18.0 && colorScore <= 2 -> HoneyGrade.A
            moisturePercent <= 21.0 && colorScore <= 4 -> HoneyGrade.B
            else -> HoneyGrade.C
        }
    }
}
