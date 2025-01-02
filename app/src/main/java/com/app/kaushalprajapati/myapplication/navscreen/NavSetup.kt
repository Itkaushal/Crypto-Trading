package com.app.kaushalprajapati.myapplication.navscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.kaushalprajapati.myapplication.mainscreens.HomeScreen
import com.app.kaushalprajapati.myapplication.mainscreens.MarketScreen
import com.app.kaushalprajapati.myapplication.mainscreens.PortfolioScreen

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController, startDestination = BottomNavDataModal.Home.route) {
        composable(BottomNavDataModal.Home.route) {
            HomeScreen(navController)
        }

        composable(BottomNavDataModal.Market.route) {
            MarketScreen()
        }

        composable(BottomNavDataModal.Portfolio.route) {
            PortfolioScreen()
        }


    }
}