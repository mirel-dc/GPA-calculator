package com.example.calculator.domain

import com.example.calculator.data.local.CourseDataSource

class InsertCourseUseCase(
    private val courseDataSource: CourseDataSource
) {
    suspend operator fun invoke(name: String, credits: Long, grade: Long, semester: Long) {
        courseDataSource.insertCourse(
            name = name,
            credits = credits,
            grade = grade,
            semester = semester
        )
    }
}