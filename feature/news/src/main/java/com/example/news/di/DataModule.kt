package com.example.news.di

import com.example.news.data.NewsRepositoryImpl
import com.example.news.domain.NewsRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

import kotlinx.serialization.json.Json
import org.koin.dsl.module


internal fun dataModule() = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single<NewsRepository> {
        NewsRepositoryImpl(
            get()
        )
    }
}