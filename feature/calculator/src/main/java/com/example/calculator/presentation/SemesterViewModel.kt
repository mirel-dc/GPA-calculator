package com.example.calculator.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.CalculateGPAUseCase
import com.example.calculator.domain.ClearAllDataUseCase
import com.example.calculator.domain.GetAllCoursesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class SemesterViewModel(
    private val calculateGPAUseCase: CalculateGPAUseCase,
    private val clearAllDataUseCase: ClearAllDataUseCase,
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
) : ViewModel() {

    private val _semesters: List<String> = listOf(
        "Semester 1", "Semester 2", "Semester 3", "Semester 4",
        "Semester 5", "Semester 6", "Semester 7", "Semester 8",
    )
    val semesters: List<String> get() = _semesters

    private val _gpa = mutableStateOf(0.0) // Состояние для хранения GPA
    val gpa: State<Double> get() = _gpa

    // Обновленный метод для работы с Flow
    init {
        viewModelScope.launch {
            calculateGPA()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun <T> Flow<List<T>>.flattenToList(): List<T> {
        return flatMapConcat { it.asFlow() }.toList()
    }

    suspend fun calculateGPA() {
        getAllCoursesUseCase().collect { courses ->
            // Здесь мы собираем Flow и вычисляем GPA
            val courseList = courses // Получаем список курсов
            Log.d("calcGPA", "Courses: $courseList")

            val result = calculateGPAUseCase(courseList)
            Log.d("calcGPA", "GPA Calculated: $result")

            // Обновляем состояние GPA
            _gpa.value = result
        }
    }

    fun resetAllData() {
        viewModelScope.launch {
            clearAllDataUseCase()
        }
    }
}