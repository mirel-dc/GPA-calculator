package com.example.news.di

import io.ktor.client.HttpClient
import org.koin.dsl.module

fun newsModule() = module {
    includes(dataModule())
    includes(domainModule())
}