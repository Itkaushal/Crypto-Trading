package com.app.kaushalprajapati.myapplication.cardScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.kaushalprajapati.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ProfileDrawerContent(navController, drawerState, scope)
        }
    ) {
        Scaffold(
            topBar = {
                // **Top Bar**
                TopAppBar(
                    title = { Text("Profile", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        androidx.compose.material.IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E1E1E))
                )
            },
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Home Screen Content", color = Color.White, fontSize = 20.sp)
                }
            }
        )
    }
}

@Composable
fun ProfileDrawerContent(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E1E1E))
            .padding(16.dp)
    ) {
        // **Profile Section**
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile), // Replace with your image
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text("Kaushal Prajapati", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("kaushal@gmail.com", color = Color.Gray, fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        // **Drawer Menu Items**
        DrawerMenuItem(icon = Icons.Default.Home, label = "Home") {
            scope.launch { drawerState.close() }
            navController.navigate("home")
        }

        DrawerMenuItem(icon = Icons.Default.AccountCircle, label = "Profile") {
            scope.launch { drawerState.close() }
            navController.navigate("profile_screen")
        }

        DrawerMenuItem(icon = Icons.Default.Settings, label = "Settings") {
            scope.launch { drawerState.close() }
            navController.navigate("settings_screen")
        }

        DrawerMenuItem(icon = Icons.Default.Create, label = "Logout") {
            scope.launch { drawerState.close() }
            // Handle logout logic here
        }
    }
}

@Composable
fun DrawerMenuItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(label, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}
