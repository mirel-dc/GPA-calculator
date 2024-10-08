package com.example.calculator.di

import com.example.calculator.domain.CalculateGPAUseCase
import com.example.calculator.domain.ClearAllDataUseCase
import com.example.calculator.domain.GetAllCoursesUseCase
import com.example.calculator.domain.GetCoursesBySemesterUseCase
import com.example.calculator.domain.InsertCourseUseCase
import com.example.calculator.domain.UpdateCourseUseCase
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

    single<ClearAllDataUseCase> {
        ClearAllDataUseCase(get())
    }

    single<GetAllCoursesUseCase> {
        GetAllCoursesUseCase(get())
    }

    single<GetCoursesBySemesterUseCase> {
        GetCoursesBySemesterUseCase(get())
    }

    single<UpdateCourseUseCase> {
        UpdateCourseUseCase(get())
    }


    viewModel<SubjectViewModel> {
        SubjectViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }

    viewModel<SemesterViewModel> {
        SemesterViewModel(
            get(),
            get(),
            get()
        )
    }
}