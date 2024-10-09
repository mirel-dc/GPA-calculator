package com.example.news.data

import android.nfc.Tag
import android.util.Log
import com.example.news.domain.NewsRepository
import com.example.news.domain.model.Article
import com.example.news.domain.model.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

private val TAG = "NewsRepositoryImpl"

class NewsRepositoryImpl(
    private val client: HttpClient
) : NewsRepository {

    override suspend fun getNews(): List<Article> {
        Log.d(TAG, "enter get news")

        val response: HttpResponse = client.get("https://newsapi.org/v2/top-headlines") {
            parameter("country", "us")
            parameter("apiKey", "bdb977d2b6c94b62a15faa0945709e84")
        }

        Log.d(TAG, "${response.status}")

        if (response.status == HttpStatusCode.OK) {
            Log.d(TAG, response.body<String>().toString())
            val newsResponse: NewsResponse = response.body()
            return newsResponse.articles
        } else {
            throw Exception("Failed to load news: ${response.status}")
        }
    }

}