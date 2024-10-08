package com.example.calculator.domain

import com.example.gpacalculator.db.CourseEntity

class CalculateGPAUseCase() {
    operator fun invoke(courses: List<CourseEntity>): Double {
        var totalGradePoints = 0f
        var totalCredits = 0f

        courses.forEach { course ->
            if (course.name != "") {
                totalGradePoints += course.grade!! * course.credits
                totalCredits += course.credits
            }
        }

        return if (totalCredits > 0) totalGradePoints.toDouble() / totalCredits else 0.0
    }
}