package com.example.calculator.domain

import com.example.calculator.data.local.CourseDataSource
import com.example.calculator.data.repository.CourseRepository

class ClearAllDataUseCase(
    private val courseDataSource: CourseDataSource
) {
    suspend operator fun invoke() {
        courseDataSource.deleteAllCourses()
    }
}