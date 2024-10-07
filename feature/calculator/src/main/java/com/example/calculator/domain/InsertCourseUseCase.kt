package com.example.calculator.domain

import com.example.calculator.data.repository.CourseRepository

class InsertCourseUseCase(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(name: String, credits: Long, grade: Long, semester: Long) {
        repository.insertCourse(name = name, credits = credits, grade = grade, semester = semester)
    }
}