package com.example.news.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.news.domain.model.Article
import com.example.news.presentation.NewsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<NewsViewModel>()

    val newsState by viewModel.newsState.collectAsState() // Подписываемся на поток новостей

    LaunchedEffect(Unit) {
        viewModel.loadNews() // Загружаем новости при первом запуске
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Top News",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (newsState.isEmpty()) {
            // Показать, если новости не загружены или список пуст
            Text(
                text = "No news available",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(newsState.size) { index ->
                        val article = newsState[index]
                        NewsItem(article)
                    }
                }
            )
        }
    }
}

@Composable
fun NewsItem(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.description ?: "No description",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}