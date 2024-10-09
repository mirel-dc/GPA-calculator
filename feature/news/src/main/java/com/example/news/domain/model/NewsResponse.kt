package com.example.news.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val articles: List<Article>
)