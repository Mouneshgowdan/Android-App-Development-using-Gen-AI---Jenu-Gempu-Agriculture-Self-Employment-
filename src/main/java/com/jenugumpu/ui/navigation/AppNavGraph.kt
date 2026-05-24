package com.jenugumpu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jenugumpu.ui.screens.dashboard.DashboardScreen
import com.jenugumpu.ui.screens.grading.GradingToolScreen
import com.jenugumpu.ui.screens.harvestlog.HarvestLogScreen
import com.jenugumpu.ui.screens.prices.PriceMonitorScreen
import com.jenugumpu.ui.screens.profit.ProfitCalculatorScreen
import com.jenugumpu.ui.screens.stock.StockScreen
import com.jenugumpu.viewmodel.HarvestViewModel
import com.jenugumpu.viewmodel.PriceViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    harvestViewModel: HarvestViewModel,
    priceViewModel: PriceViewModel
) {
    NavHost(navController = navController, startDestination = Routes.Dashboard) {
        composable(Routes.Dashboard) {
            DashboardScreen(
                onHarvestClick = { navController.navigate(Routes.HarvestLog) },
                onGradingClick = { navController.navigate(Routes.Grading) },
                onStockClick = { navController.navigate(Routes.Stock) },
                onPricesClick = { navController.navigate(Routes.Prices) },
                onProfitClick = { navController.navigate(Routes.Profit) }
            )
        }
        composable(Routes.HarvestLog) {
            HarvestLogScreen(viewModel = harvestViewModel)
        }
        composable(Routes.Grading) {
            GradingToolScreen()
        }
        composable(Routes.Stock) {
            StockScreen(viewModel = harvestViewModel)
        }
        composable(Routes.Prices) {
            PriceMonitorScreen(viewModel = priceViewModel)
        }
        composable(Routes.Profit) {
            ProfitCalculatorScreen()
        }
    }
}
