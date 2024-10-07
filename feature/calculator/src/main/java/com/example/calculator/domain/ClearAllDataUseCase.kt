package com.example.calculator.domain

import com.example.calculator.data.repository.CourseRepository

class ClearAllDataUseCase(
    private val repository: CourseRepository
) {
    suspend operator fun invoke() {
        repository.deleteAllCourses()
    }
}