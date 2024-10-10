package com.example.login__signin_screen

import android.adservices.topics.Topic
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login__signin_screen.data.model.NewsResponse
import com.example.login__signin_screen.data.network.RetrtofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// survive configuration changes
class MainViewModel : ViewModel() {
    private val _newsResponse = MutableStateFlow<NewsResponse?>(null)
    val newsResponse: StateFlow<NewsResponse?> get()  = _newsResponse
    val apiService = RetrtofitInstance.apiClient
    fun getNews(topic: String) {
        viewModelScope.launch {
            val response =
                apiService.getTopHeadLines(q = topic, apiKey = "83b239da7eb34781b7efa27976cb11dc")
            if (response.isSuccessful) {
                _newsResponse.value = response.body()
            }
        }


    }}