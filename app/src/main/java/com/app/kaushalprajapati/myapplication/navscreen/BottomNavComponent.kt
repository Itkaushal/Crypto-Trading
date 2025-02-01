package com.app.kaushalprajapati.myapplication.navscreen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Colors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavDataModal.Home,
        BottomNavDataModal.Market,
        BottomNavDataModal.Portfolio
    )

    BottomNavigation(
        backgroundColor = Color(0xFF1E1E1E),
        contentColor = Color.White,
        elevation = 50.dp,
        modifier = Modifier.fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { items ->
            BottomNavigationItem(
                icon = { Icon(items.icon, contentDescription = items.title, tint = Color.Magenta.copy(alpha = 0.4f), modifier = Modifier.padding(top = 10.dp)) },
                alwaysShowLabel = true,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White,
                label = {Text(items.title, style = TextStyle(color = Color.White.copy(alpha = 0.8f), fontWeight = FontWeight.Bold), modifier = Modifier.padding(bottom = 10.dp).padding(top = 10.dp))},
                selected = currentRoute == items.route,
                onClick = {
                    if (currentRoute != items.route) {
                        navController.navigate(items.route)
                    }
                },

            )

        }
    }
}
