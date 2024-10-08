package com.example.calculator.di

import com.example.calculator.data.local.CourseDataSource
import com.example.calculator.data.repository.CourseRepository
import org.koin.dsl.module

internal fun dataModule() = module {
    single<CourseDataSource> {
        CourseRepository(get())
    }
}