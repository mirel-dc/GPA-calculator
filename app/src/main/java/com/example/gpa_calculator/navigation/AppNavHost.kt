package com.example.gpa_calculator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.calculator.ui.SemesterScreen
import com.example.calculator.ui.SubjectScreen
import com.example.news.ui.NewsScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = NavRoutes.SEMESTER_SCREEN) {
        // Экран семестров
        composable(NavRoutes.SEMESTER_SCREEN) {
            SemesterScreen(
                onSemesterClick = { semester ->
                    navController.navigate("${NavRoutes.SUBJECT_SCREEN}/$semester")
                },
                onResetClick = { /* обработка сброса */ }
            )
        }

        // Экран предметов с передачей аргумента номера семестра
        composable(
            route = "${NavRoutes.SUBJECT_SCREEN}/{semester}",
            arguments = listOf(navArgument("semester") {
                type = NavType.IntType // тип аргумента
            })
        ) { backStackEntry ->
            val semester = backStackEntry.arguments?.getInt("semester") ?: 0
            SubjectScreen(
                semester = semester,
                onBackClick = { navController.popBackStack() }
            )
        }

        // Новый экран новостей
        composable(NavRoutes.NEWS_SCREEN) {
            NewsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}