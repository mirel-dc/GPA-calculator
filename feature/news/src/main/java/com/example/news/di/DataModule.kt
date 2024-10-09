package com.example.news.di

import com.example.news.data.NewsRepositoryImpl
import com.example.news.domain.NewsRepository
import io.ktor.client.HttpClient
import org.koin.dsl.module

internal fun dataModule() = module {
    single<HttpClient> {
        HttpClient()
    }

    single<NewsRepository> {
        NewsRepositoryImpl(
            get()
        )
    }
}