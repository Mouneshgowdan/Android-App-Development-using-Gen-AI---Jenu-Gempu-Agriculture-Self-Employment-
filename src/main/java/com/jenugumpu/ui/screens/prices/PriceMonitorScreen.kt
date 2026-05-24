package com.jenugumpu.ui.screens.prices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jenugumpu.R
import com.jenugumpu.viewmodel.PriceViewModel

@Composable
fun PriceMonitorScreen(viewModel: PriceViewModel) {
    val latestPrice by viewModel.latestPrice.collectAsState()

    LaunchedEffect(Unit) {
        if (latestPrice == null) {
            viewModel.refreshPrices()
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.price_monitor),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val retail = latestPrice?.retailPricePerKg ?: 0.0
                    val wholesale = latestPrice?.wholesalePricePerKg ?: 0.0
                    Text("${stringResource(R.string.retail_price)}: ₹%.0f / ಕೆ.ಜಿ.".format(retail))
                    Text("${stringResource(R.string.wholesale_price)}: ₹%.0f / ಕೆ.ಜಿ.".format(wholesale))
                    Text("ವ್ಯತ್ಯಾಸ: ₹%.0f".format(retail - wholesale))
                    Text("ಮಾದರಿ ಮಾರುಕಟ್ಟೆ ಡೇಟಾ", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
        item {
            Button(
                onClick = viewModel::refreshPrices,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.refresh))
            }
        }
    }
}
