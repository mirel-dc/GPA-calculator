package com.example.news.domain

import com.example.news.domain.model.Article

class GetNewsUseCase(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(): List<Article> {
        return newsRepository.getNews()
    }
}