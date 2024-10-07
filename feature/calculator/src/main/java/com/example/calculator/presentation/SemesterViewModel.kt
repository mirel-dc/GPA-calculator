package com.example.calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.ClearAllDataUseCase
import kotlinx.coroutines.launch

class SemesterViewModel(
    private val clearAllDataUseCase: ClearAllDataUseCase
) : ViewModel() {

    fun resetAllData() {
        viewModelScope.launch {
            clearAllDataUseCase()
        }
    }

}