package com.example.calculator.domain

class CalculateGPAUseCase() {
    operator fun invoke(courses: List<Course>): Double {
        var totalGradePoints = 0
        var totalCredits = 0

        courses.forEach { course ->
            totalGradePoints += course.grade * course.credits
            totalCredits += course.credits
        }

        return if (totalCredits > 0) totalGradePoints.toDouble() / totalCredits else 0.0
    }
}