package com.app.kaushalprajapati.myapplication.navscreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.kaushalprajapati.myapplication.cardScreens.EarnExtraScreen
import com.app.kaushalprajapati.myapplication.cardScreens.InstantBuyScreen
import com.app.kaushalprajapati.myapplication.cardScreens.LearningScreen
import com.app.kaushalprajapati.myapplication.cardScreens.NotificationScreen
import com.app.kaushalprajapati.myapplication.cardScreens.ProfileScreen
import com.app.kaushalprajapati.myapplication.cardScreens.StartSipScreen
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
            MarketScreen(navController)
        }

        composable(BottomNavDataModal.Portfolio.route) {
            PortfolioScreen(navController)
        }

        composable("instant_buy"){
            InstantBuyScreen(navController)
        }

        composable("earn_extra"){
            EarnExtraScreen(navController)

        }

        composable("start_sip"){
            StartSipScreen(navController)
        }

        composable("learning"){
            LearningScreen(navController)
        }

        composable("notification_screen"){
            NotificationScreen(navController)
        }

        composable("profile_screen"){
            ProfileScreen(navController)
        }

    }
}