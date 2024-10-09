package com.example.news.domain

import com.example.news.domain.model.Article

interface NewsRepository {
    suspend fun getNews(): List<Article>
}