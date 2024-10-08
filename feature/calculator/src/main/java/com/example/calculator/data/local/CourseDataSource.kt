package com.example.calculator.data.local

import com.example.gpacalculator.db.CourseEntity
import kotlinx.coroutines.flow.Flow

//По хорошему закинуть в domain и сделать маппер для CourseEntity
interface CourseDataSource {

    fun getCoursesBySemester(semester: Long): Flow<List<CourseEntity>>

    fun getAllCourses(): Flow<List<CourseEntity>>

    suspend fun deleteAllCourses()

    suspend fun insertCourse(
        name: String,
        credits: Long,
        grade: Long?,
        semester: Long?,
    )

    suspend fun updateCourse(
        id: Long,
        name: String,
        credits: Long,
        grade: Long?,
        semester: Long?,
    )
}