package com.example.calculator.di

import com.example.calculator.data.repository.CourseRepository
import org.koin.dsl.module

internal fun dataModule() = module {
    single<CourseRepository> {
        CourseRepository(get())
    }
}