package com.example.calculator.di

import com.example.calculator.domain.CalculateGPAUseCase
import com.example.calculator.domain.InsertCourseUseCase
import com.example.calculator.presentation.SemesterViewModel
import com.example.calculator.presentation.SubjectViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal fun domainModule() = module {
    single<CalculateGPAUseCase> {
        CalculateGPAUseCase()
    }

    single<InsertCourseUseCase> {
        InsertCourseUseCase(get())
    }

    viewModel<SubjectViewModel> {
        SubjectViewModel(
            get(),
            get()
        )
    }

    viewModel<SemesterViewModel> {
        SemesterViewModel()
    }
}