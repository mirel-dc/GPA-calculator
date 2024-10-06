package com.example.gpa_calculator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.calculator.ui.SemesterScreen
import com.example.calculator.ui.SubjectScreen

@Composable
fun GPAApp() {
    var currentScreen by remember { mutableStateOf("semesters") }
    var selectedSemester by remember { mutableStateOf(1) }

    when (currentScreen) {
        "semesters" -> SemesterScreen(
            onSemesterClick = {
                selectedSemester = it
                currentScreen = "subjects"
            },
            onResetClick = {
                // Обработка сброса данных
            }
        )

        "subjects" -> SubjectScreen(
            semester = selectedSemester,
            onBackClick = { currentScreen = "semesters" },
            onAddSubjectClick = {}
        )
    }
}