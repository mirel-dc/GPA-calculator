package com.example.calculator.presentation

import androidx.lifecycle.ViewModel
import com.example.calculator.domain.CalculateGPAUseCase
import com.example.calculator.domain.InsertCourseUseCase

class SubjectViewModel(
    val calculateGPAUseCase: CalculateGPAUseCase,
    val insertCourseUseCase: InsertCourseUseCase,
) : ViewModel() {

}