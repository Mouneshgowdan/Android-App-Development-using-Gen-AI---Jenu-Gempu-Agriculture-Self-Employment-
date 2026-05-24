package com.jenugumpu.ui.screens.profit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jenugumpu.R
import com.jenugumpu.viewmodel.ProfitViewModel

@Composable
fun ProfitCalculatorScreen(
    viewModel: ProfitViewModel = viewModel()
) {
    val summary by viewModel.summary.collectAsState()
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var travel by remember { mutableStateOf("") }
    var container by remember { mutableStateOf("") }
    var labor by remember { mutableStateOf("") }
    var other by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.profit_calculator),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                DecimalField(quantity, "ಪ್ರಮಾಣ ಕೆ.ಜಿ.") { quantity = it }
                DecimalField(price, "ಬೆಲೆ / ಕೆ.ಜಿ.") { price = it }
                DecimalField(travel, "ಪ್ರಯಾಣ ವೆಚ್ಚ") { travel = it }
                DecimalField(container, "ಡಬ್ಬಿ ವೆಚ್ಚ") { container = it }
                DecimalField(labor, "ಕೆಲಸ ವೆಚ್ಚ") { labor = it }
                DecimalField(other, "ಇತರೆ ವೆಚ್ಚ") { other = it }
                Button(
                    onClick = {
                        viewModel.calculate(
                            quantityKg = quantity.toDoubleOrNull() ?: 0.0,
                            pricePerKg = price.toDoubleOrNull() ?: 0.0,
                            travelCost = travel.toDoubleOrNull() ?: 0.0,
                            containerCost = container.toDoubleOrNull() ?: 0.0,
                            laborCost = labor.toDoubleOrNull() ?: 0.0,
                            otherCost = other.toDoubleOrNull() ?: 0.0
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("ಲೆಕ್ಕ ಹಾಕಿ")
                }
            }
        }
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text("ಒಟ್ಟು ಆದಾಯ: ₹%.2f".format(summary.revenue))
                    Text("ಒಟ್ಟು ವೆಚ್ಚ: ₹%.2f".format(summary.totalCost))
                    Text(
                        text = "${stringResource(R.string.profit)}: ₹%.2f".format(summary.profit),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun DecimalField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = Modifier.fillMaxWidth()
    )
}
