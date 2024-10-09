package com.example.news.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.GetNewsUseCase
import com.example.news.domain.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _newsState = MutableStateFlow<List<Article>>(emptyList())
    val newsState: StateFlow<List<Article>> = _newsState

    fun loadNews() {
        viewModelScope.launch {
            _newsState.value = getNewsUseCase()
//            try {
//                Log.d("VM", "Entered try")
//                val news = getNewsUseCase()
//                _newsState.value = news
//                Log.d("VM", _newsState.value[1].toString())
//            } catch (e: Exception) {
//                // Обработка ошибки
//                Log.d("VM", "in catch")
//                _newsState.value = emptyList()
//            }
        }
    }
}