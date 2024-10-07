package com.example.calculator.di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.calculator.CoursesDatabase
import com.example.gpacalculator.db.CoursesQueries
import org.koin.dsl.module

internal fun sqlDelightModule() = module {
    single<AndroidSqliteDriver> {
        AndroidSqliteDriver(
            schema = CoursesDatabase.Schema,    // Схема базы данных
            context = get(),                     // Контекст приложения
            name = "gpa_calculator.db"  // Имя файла базы данных
        )
    }

    single<CoursesDatabase> {
        CoursesDatabase(get())
    }

    single<CoursesQueries> {
        val db: CoursesDatabase = get()
        db.coursesQueries
    }
}