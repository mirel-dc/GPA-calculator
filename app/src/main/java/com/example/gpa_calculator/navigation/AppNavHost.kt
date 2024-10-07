package com.example.gpa_calculator.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.calculator.ui.SemesterScreen
import com.example.calculator.ui.SubjectScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = NavRoutes.SemesterScreen) {
        // Экран семестров
        composable(NavRoutes.SemesterScreen) {
            SemesterScreen(
                onSemesterClick = { semester ->
                    Log.d("ABC", semester.toString())
                    navController.navigate("${NavRoutes.SubjectScreen}/$semester")
                },
                onResetClick = { /* обработка сброса */ }
            )
        }

        // Экран предметов с передачей аргумента номера семестра
        composable(
            route = "${NavRoutes.SubjectScreen}/{semester}",
            arguments = listOf(navArgument("semester") {
                type = NavType.IntType // тип аргумента
            })
        ) { backStackEntry ->
            // Получаем номер семестра из аргументов
            val semester = backStackEntry.arguments?.getInt("semester") ?: 0
            SubjectScreen(
                semester = semester,
                onAddSubjectClick = { /* обработка добавления предмета */ },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}