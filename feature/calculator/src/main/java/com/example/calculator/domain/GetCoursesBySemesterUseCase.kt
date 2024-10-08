package com.example.calculator.domain

import com.example.calculator.data.local.CourseDataSource
import com.example.calculator.data.repository.CourseRepository
import com.example.gpacalculator.db.CourseEntity
import kotlinx.coroutines.flow.Flow

class GetCoursesBySemesterUseCase(
    private val courseDataSource: CourseDataSource
) {
    operator fun invoke(semester: Long): Flow<List<CourseEntity>> {
        return courseDataSource.getCoursesBySemester(semester)
    }
}