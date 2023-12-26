package data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiClient {
    private const val BASE_URL = "https://newsdata.io/api/1/"

    val apiService: NewsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }
}