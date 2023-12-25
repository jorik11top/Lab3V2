package com.example.lab3v2.network
import com.example.lab3v2.api.NewsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object NewsApiClient {
    private const val BASE_URL = "https://newsdata.io/"

    val apiService: NewsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(NewsApiService::class.java)
    }
}