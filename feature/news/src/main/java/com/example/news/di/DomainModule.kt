package com.example.news.di

import com.example.news.domain.GetNewsUseCase
import com.example.news.presentation.NewsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal fun domainModule() = module {
    single<GetNewsUseCase> {
        GetNewsUseCase(get())
    }

    viewModel<NewsViewModel> {
        get()
    }
}