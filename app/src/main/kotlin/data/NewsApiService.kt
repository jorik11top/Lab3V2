package data

import model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("news")
    suspend fun getNews(@Query("apikey") apiKey: String, @Query("q") keyword: String): NewsResponse
}