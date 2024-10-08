package com.example.calculator.domain

import com.example.calculator.data.local.CourseDataSource

class ClearAllDataUseCase(
    private val courseDataSource: CourseDataSource
) {
    suspend operator fun invoke() {
        courseDataSource.deleteAllCourses()
    }
}