package com.jenugumpu.ui.screens.grading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jenugumpu.R
import com.jenugumpu.ui.components.GradeCard
import com.jenugumpu.viewmodel.GradingViewModel
import kotlin.math.roundToInt

@Composable
fun GradingToolScreen(
    viewModel: GradingViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val colorGuide = remember {
        listOf(
            Color(0xFFFFD15C),
            Color(0xFFEAA33A),
            Color(0xFFC47D20),
            Color(0xFF8B521C),
            Color(0xFF513016)
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.grading_tool),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("ತೇವಾಂಶ: %.1f%%".format(state.moisturePercent))
                    Slider(
                        value = state.moisturePercent.toFloat(),
                        onValueChange = { viewModel.setMoisture(it.toDouble()) },
                        valueRange = 14f..25f
                    )
                    Text("ಬಣ್ಣದ ಮಟ್ಟ: ${state.colorScore}")
                    Slider(
                        value = state.colorScore.toFloat(),
                        onValueChange = { viewModel.setColorScore(it.roundToInt().coerceIn(1, 5)) },
                        valueRange = 1f..5f,
                        steps = 3
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        colorGuide.forEach { color ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(32.dp)
                                    .background(color)
                            )
                        }
                    }
                }
            }
        }
        item {
            GradeCard(
                grade = state.grade,
                selected = true
            )
        }
    }
}
