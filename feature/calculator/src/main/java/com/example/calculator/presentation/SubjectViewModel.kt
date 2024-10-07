package com.example.calculator.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.CalculateGPAUseCase
import com.example.calculator.domain.Course
import com.example.calculator.domain.InsertCourseUseCase

class SubjectViewModel(
    val calculateGPAUseCase: CalculateGPAUseCase,
    val insertCourseUseCase: InsertCourseUseCase,
) : ViewModel() {
    val list = mutableStateOf(
        MutableList(6) { Course("", 5, 5) }
    )
}