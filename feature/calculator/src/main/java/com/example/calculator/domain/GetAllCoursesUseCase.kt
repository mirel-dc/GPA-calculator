package com.example.calculator.domain

import com.example.calculator.data.local.CourseDataSource
import com.example.calculator.data.repository.CourseRepository
import com.example.gpacalculator.db.CourseEntity
import kotlinx.coroutines.flow.Flow

class GetAllCoursesUseCase(
    private val courseDataSource: CourseDataSource
) {
    operator fun invoke(): Flow<List<CourseEntity>> {
        return courseDataSource.getAllCourses()
    }
}