package com.example.calculator.domain

import com.example.calculator.data.repository.CourseRepository
import com.example.gpacalculator.db.CourseEntity
import kotlinx.coroutines.flow.Flow

class GetCoursesBySemesterUseCase(
    private val repository: CourseRepository
) {
    operator fun invoke(semester: Long): Flow<List<CourseEntity>> {
        return repository.getCoursesBySemester(semester)
    }
}