package com.example.calculator.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.calculator.data.local.CourseDataSource
import com.example.gpacalculator.db.CourseEntity
import com.example.gpacalculator.db.CoursesQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CourseRepository(private val queries: CoursesQueries) : CourseDataSource {

    override fun getCoursesBySemester(semester: Long): Flow<List<CourseEntity>> {
        return queries.getCoursesBySemester(semester).asFlow().mapToList(Dispatchers.IO)
    }

    override fun getAllCourses(): Flow<List<CourseEntity>> {
        return queries.getAllCourses().asFlow().mapToList(Dispatchers.IO)
    }

    override suspend fun deleteAllCourses() {
        queries.deleteAll()
    }

    override suspend fun insertCourse(name: String, credits: Long, grade: Long?, semester: Long?) {
        withContext(Dispatchers.IO) {
            queries.insertCourse(name = name, credits = credits, grade = grade, semester = semester)
        }
    }

    override suspend fun updateCourse(
        id: Long,
        name: String,
        credits: Long,
        grade: Long?,
        semester: Long?
    ) {
        withContext(Dispatchers.IO) {
            queries.updateCourse(
                name = name,
                credits = credits,
                grade = grade,
                semester = semester,
                id = id
            )
        }
    }
}
