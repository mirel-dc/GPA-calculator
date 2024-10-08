package com.example.calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.CalculateGPAUseCase
import com.example.calculator.domain.GetAllCoursesUseCase
import com.example.calculator.domain.GetCoursesBySemesterUseCase
import com.example.calculator.domain.InsertCourseUseCase
import com.example.calculator.domain.UpdateCourseUseCase
import com.example.gpacalculator.db.CourseEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SubjectViewModel(
    private val calculateGPAUseCase: CalculateGPAUseCase,
    private val insertCourseUseCase: InsertCourseUseCase,
    private val getCoursesBySemesterUseCase: GetCoursesBySemesterUseCase,
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
    private val updateCourseUseCase: UpdateCourseUseCase,
) : ViewModel() {

    private val _subjects = MutableStateFlow<List<CourseEntity>>(emptyList())
    val subjects: StateFlow<List<CourseEntity>> get() = _subjects
//
//
//    init {
//        viewModelScope.launch {
//            getAllCoursesUseCase()
//                .stateIn(viewModelScope)
//                .collect { courseEntities ->
//                    _subjects.value = courseEntities
//                }
//        }
//    }

    fun initializeSubjects(semester: Long) {
        viewModelScope.launch {
            // Проверка есть ли уже предметы для этого семестра
            val existingSubjects = getCoursesBySemesterUseCase(semester).firstOrNull()
            if (existingSubjects.isNullOrEmpty()) {
                // Если предметов нет, добавляем 6 пустых записей
                repeat(6) {
                    insertCourseUseCase(
                        name = "",
                        credits = 3,  // Задать по умолчанию
                        grade = 0,    // Задать по умолчанию
                        semester = semester
                    )
                }
            }
            // После инициализации, подтягиваем предметы
            getSubjectsForSemester(semester)
        }
    }

    fun getSubjectsForSemester(semester: Long) {
        viewModelScope.launch {
            getCoursesBySemesterUseCase(semester)
                .collect { subjects ->
                    _subjects.value = subjects
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

    fun updateSubject(updatedSubject: CourseEntity) {
        viewModelScope.launch {
            updateCourseUseCase(
                updatedSubject.id,
                updatedSubject.credits,
                updatedSubject.grade,
                updatedSubject.name,
                updatedSubject.semester
            )
            _subjects.value =
                _subjects.value.map { if (it.id == updatedSubject.id) updatedSubject else it }
        }
    }
}