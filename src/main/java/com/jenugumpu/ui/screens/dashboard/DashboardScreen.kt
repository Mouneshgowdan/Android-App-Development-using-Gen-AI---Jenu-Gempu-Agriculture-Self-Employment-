package com.jenugumpu.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jenugumpu.R

@Composable
fun DashboardScreen(
    onHarvestClick: () -> Unit,
    onGradingClick: () -> Unit,
    onStockClick: () -> Unit,
    onPricesClick: () -> Unit,
    onProfitClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "ಜೇನು ಉತ್ಪಾದಕರ ಒಕ್ಕೂಟ",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        item { DashboardCard("ಕೊಯ್ಲು ದಾಖಲಿಸಿ", "ಬ್ಯಾಚ್, ಸ್ಥಳ, ಪ್ರಮಾಣ", onHarvestClick) }
        item { DashboardCard("ಗುಣಮಟ್ಟ ಪರೀಕ್ಷೆ", "ತೇವಾಂಶ ಮತ್ತು ಬಣ್ಣದ ಆಧಾರ", onGradingClick) }
        item { DashboardCard("ಸ್ಟಾಕ್ ಮಾಹಿತಿ", "ಒಟ್ಟು ಜೇನು ಪ್ರಮಾಣ", onStockClick) }
        item { DashboardCard("ಬೆಲೆ ಹೋಲಿಕೆ", "ಚಿಲ್ಲರೆ ಮತ್ತು ಮಾರಾಟಗಾರರ ಬೆಲೆ", onPricesClick) }
        item { DashboardCard("ಲಾಭ ಲೆಕ್ಕ", "ವೆಚ್ಚದ ನಂತರ ಆದಾಯ", onProfitClick) }
    }
}

@Composable
private fun DashboardCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
