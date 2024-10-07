package com.example.calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.CalculateGPAUseCase
import com.example.calculator.domain.GetAllCoursesUseCase
import com.example.calculator.domain.GetCoursesBySemesterUseCase
import com.example.calculator.domain.InsertCourseUseCase
import com.example.gpacalculator.db.CourseEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SubjectViewModel(
    private val calculateGPAUseCase: CalculateGPAUseCase,
    private val insertCourseUseCase: InsertCourseUseCase,
    private val getCoursesBySemesterUseCase: GetCoursesBySemesterUseCase,
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
) : ViewModel() {

    private val _subjects = MutableStateFlow<List<CourseEntity>>(emptyList())
    val subjects: StateFlow<List<CourseEntity>> get() = _subjects


    init {
        viewModelScope.launch {
            getAllCoursesUseCase()
                .stateIn(viewModelScope)
                .collect { courseEntities ->
                    _subjects.value = courseEntities
                }
        }
    }


    fun addSubject(
        name: String,
        credits: Long,
        grade: Long,
        semester: Long
    ) {
        viewModelScope.launch {
            insertCourseUseCase(
                name = name,
                credits = credits,
                grade = grade,
                semester = semester
            )
            //_subjects.value += newSubject
        }
    }

    fun updateSubject(updatedSubject: com.example.calculator.domain.Course) {
        TODO()
//        viewModelScope.launch {
//            coursesRepository.updateCourse(updatedSubject)
//            _subjects.value =
//                _subjects.value.map { if (it.id == updatedSubject.id) updatedSubject else it }
//        }
    }
}