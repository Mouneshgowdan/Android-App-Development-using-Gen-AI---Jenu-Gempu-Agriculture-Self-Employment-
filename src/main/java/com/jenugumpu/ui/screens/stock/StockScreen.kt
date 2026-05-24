package com.jenugumpu.ui.screens.stock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jenugumpu.R
import com.jenugumpu.domain.model.FloralSource
import com.jenugumpu.viewmodel.HarvestViewModel

@Composable
fun StockScreen(viewModel: HarvestViewModel) {
    val totalStock by viewModel.totalStockKg.collectAsState()
    val stockBySource by viewModel.stockBySource.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.stock_tracker),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("ಒಟ್ಟು ಸ್ಟಾಕ್", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "%.2f ಕೆ.ಜಿ.".format(totalStock),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
        items(stockBySource) { source ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(labelForSource(source.floralSource), style = MaterialTheme.typography.titleMedium)
                    Text("%.2f ಕೆ.ಜಿ.".format(source.totalKg))
                }
            }
        }
    }
}

private fun labelForSource(value: String): String {
    return FloralSource.entries.firstOrNull { it.name == value }?.labelKn ?: value
}
