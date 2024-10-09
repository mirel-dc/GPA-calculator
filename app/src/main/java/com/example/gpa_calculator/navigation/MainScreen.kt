package com.example.gpa_calculator.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Semesters") },
                    label = { Text("Semesters") },
                    selected = false, // можете настроить выбор по текущему маршруту
                    onClick = { navController.navigate(NavRoutes.SEMESTER_SCREEN) }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "News") },
                    label = { Text("News") },
                    selected = false, // настройте выбор по текущему маршруту
                    onClick = { navController.navigate(NavRoutes.NEWS_SCREEN) }
                )
            }
        }
    ) {
        // Вызываем навигацию внутри Scaffold
        AppNavHost(navController = navController)
    }
}