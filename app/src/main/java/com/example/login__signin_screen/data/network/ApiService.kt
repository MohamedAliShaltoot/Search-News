package com.example.login__signin_screen.data.network

import com.example.login__signin_screen.data.model.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getTopHeadLines(@Query("q") q: String,@Query("apiKey") apiKey: String): Response<NewsResponse>
}

object RetrtofitInstance{

    // Create a logging interceptor
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Logs the request and response body
    }

    // Create an OkHttpClient and add the logging interceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()



    val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiClient = retrofit.create(ApiService::class.java)


}