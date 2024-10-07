package com.example.calculator.di

import org.koin.dsl.module

fun calculatorModule() = module {
    includes(dataModule())
    includes(sqlDelightModule())
    includes(dataModule())
    includes(domainModule())
}