package com.jenugumpu.domain.usecase

import com.jenugumpu.domain.model.HoneyGrade
import org.junit.Assert.assertEquals
import org.junit.Test

class SimulateGradeUseCaseTest {
    private val useCase = SimulateGradeUseCase()

    @Test
    fun lowMoistureAndLightColorReturnsGradeA() {
        val grade = useCase.gradeHoney(moisturePercent = 17.5, colorScore = 2)

        assertEquals(HoneyGrade.A, grade)
    }

    @Test
    fun mediumMoistureReturnsGradeB() {
        val grade = useCase.gradeHoney(moisturePercent = 20.0, colorScore = 3)

        assertEquals(HoneyGrade.B, grade)
    }

    @Test
    fun highMoistureReturnsGradeC() {
        val grade = useCase.gradeHoney(moisturePercent = 23.0, colorScore = 5)

        assertEquals(HoneyGrade.C, grade)
    }
}
