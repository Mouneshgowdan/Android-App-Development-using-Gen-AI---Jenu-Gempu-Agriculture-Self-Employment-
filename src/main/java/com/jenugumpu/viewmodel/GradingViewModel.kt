package com.jenugumpu.viewmodel

import androidx.lifecycle.ViewModel
import com.jenugumpu.domain.model.HoneyGrade
import com.jenugumpu.domain.usecase.SimulateGradeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class GradingUiState(
    val moisturePercent: Double = 18.0,
    val colorScore: Int = 2,
    val grade: HoneyGrade = HoneyGrade.A
)

class GradingViewModel(
    private val simulateGradeUseCase: SimulateGradeUseCase = SimulateGradeUseCase()
) : ViewModel() {
    private val _uiState = MutableStateFlow(GradingUiState())
    val uiState: StateFlow<GradingUiState> = _uiState.asStateFlow()

    fun setMoisture(value: Double) {
        _uiState.update { current ->
            val grade = simulateGradeUseCase.gradeHoney(value, current.colorScore)
            current.copy(moisturePercent = value, grade = grade)
        }
    }

    fun setColorScore(value: Int) {
        _uiState.update { current ->
            val grade = simulateGradeUseCase.gradeHoney(current.moisturePercent, value)
            current.copy(colorScore = value, grade = grade)
        }
    }
}
