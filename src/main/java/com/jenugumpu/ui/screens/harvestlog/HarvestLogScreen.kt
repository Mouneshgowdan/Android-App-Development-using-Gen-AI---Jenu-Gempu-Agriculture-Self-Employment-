package com.jenugumpu.ui.screens.harvestlog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jenugumpu.R
import com.jenugumpu.domain.model.FloralSource
import com.jenugumpu.domain.model.HoneyGrade
import com.jenugumpu.viewmodel.HarvestViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HarvestLogScreen(viewModel: HarvestViewModel) {
    val harvests by viewModel.harvests.collectAsState()
    var batchId by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var source by remember { mutableStateOf(FloralSource.WILDFLOWER) }
    var grade by remember { mutableStateOf(HoneyGrade.B) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.harvest_log),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = batchId,
                        onValueChange = { batchId = it },
                        label = { Text(stringResource(R.string.batch_id)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = location,
                        onValueChange = { location = it },
                        label = { Text(stringResource(R.string.location)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = quantity,
                        onValueChange = { quantity = it },
                        label = { Text(stringResource(R.string.quantity_kg)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text("ಹೂವಿನ ಮೂಲ", style = MaterialTheme.typography.titleMedium)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FloralSource.entries.take(3).forEach { option ->
                            FilterChip(
                                selected = source == option,
                                onClick = { source = option },
                                label = { Text(option.labelKn) }
                            )
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FloralSource.entries.drop(3).forEach { option ->
                            FilterChip(
                                selected = source == option,
                                onClick = { source = option },
                                label = { Text(option.labelKn) }
                            )
                        }
                    }
                    Text("ಗುಣಮಟ್ಟ", style = MaterialTheme.typography.titleMedium)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        HoneyGrade.entries.forEach { option ->
                            FilterChip(
                                selected = grade == option,
                                onClick = { grade = option },
                                label = { Text("${option.icon} ${option.labelKn}") }
                            )
                        }
                    }
                    Button(
                        onClick = {
                            val kg = quantity.toDoubleOrNull()
                            if (batchId.isNotBlank() && location.isNotBlank() && kg != null && kg > 0.0) {
                                viewModel.addHarvest(batchId, location, kg, source, grade)
                                batchId = ""
                                location = ""
                                quantity = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.save))
                    }
                }
            }
        }
        item {
            Text("ಇತ್ತೀಚಿನ ದಾಖಲೆಗಳು", style = MaterialTheme.typography.titleLarge)
        }
        items(harvests) { entry ->
            HarvestEntryCard(
                batchId = entry.batchId,
                location = entry.location,
                quantityKg = entry.quantityKg,
                floralSource = entry.floralSource,
                grade = entry.finalGrade,
                dateMillis = entry.harvestDate
            )
        }
    }
}

@Composable
private fun HarvestEntryCard(
    batchId: String,
    location: String,
    quantityKg: Double,
    floralSource: String,
    grade: String,
    dateMillis: Long
) {
    val date = remember(dateMillis) {
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(dateMillis))
    }
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text("ಬ್ಯಾಚ್: $batchId", style = MaterialTheme.typography.titleMedium)
            Text("$location • $date")
            Text("ಪ್ರಮಾಣ: $quantityKg ಕೆ.ಜಿ. • ಮೂಲ: $floralSource • ಗ್ರೇಡ್: $grade")
        }
    }
}
