package com.example.lab3v2.api
import com.example.lab3v2.model.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

object ApiKey {
    const val KEY = "pub_3536319d3a2d8ad98073d63d0ca2d6b21dbda"
}

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = ApiKey.KEY
    ): Response<NewsResponse>
}

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String
)