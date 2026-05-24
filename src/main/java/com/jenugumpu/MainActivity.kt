package com.jenugumpu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.jenugumpu.ui.navigation.AppNavGraph
import com.jenugumpu.ui.theme.JenuGumpuTheme
import com.jenugumpu.viewmodel.HarvestViewModel
import com.jenugumpu.viewmodel.HarvestViewModelFactory
import com.jenugumpu.viewmodel.PriceViewModel
import com.jenugumpu.viewmodel.PriceViewModelFactory

class MainActivity : ComponentActivity() {
    private val harvestViewModel: HarvestViewModel by viewModels {
        val app = application as JenuGumpuApplication
        HarvestViewModelFactory(app.harvestRepository)
    }

    private val priceViewModel: PriceViewModel by viewModels {
        val app = application as JenuGumpuApplication
        PriceViewModelFactory(app.priceRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JenuGumpuTheme {
                AppNavGraph(
                    navController = rememberNavController(),
                    harvestViewModel = harvestViewModel,
                    priceViewModel = priceViewModel
                )
            }
        }
    }
}
