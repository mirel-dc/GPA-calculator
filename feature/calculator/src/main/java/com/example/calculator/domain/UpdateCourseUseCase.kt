package com.example.calculator.domain

import com.example.calculator.data.local.CourseDataSource

class UpdateCourseUseCase(
    private val courseDataSource: CourseDataSource
) {
    suspend operator fun invoke(
        id: Long,
        credits: Long,
        grade: Long?,
        name: String,
        semester: Long?
    ) {
        courseDataSource.updateCourse(
            id = id,
            name = name,
            credits = credits,
            grade = grade,
            semester = semester
        )
    }
}