package com.example.lab3v2

import NewsAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3v2.api.NewsResponse
import com.example.lab3v2.model.News
import com.example.lab3v2.network.NewsApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        val searchButton: Button = findViewById(R.id.searchButton)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(emptyList())
        recyclerView.adapter = newsAdapter

        searchButton.setOnClickListener {
            // Ваш ключ API
            val apiKey = "pub_3536319d3a2d8ad98073d63d0ca2d6b21dbda"
            val query = searchEditText.text.toString()

            // Выполняем запрос к API
            NewsApiClient.apiService.getTopHeadlines("us", apiKey)
                .enqueue(object : Callback<NewsResponse> {
                    override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                        if (response.isSuccessful) {
                            val newsResponse = response.body()
                            val newsList = newsResponse?.articles ?: emptyList()
                            newsAdapter.updateData(newsList)
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Ошибка при загрузке новостей",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        Toast.makeText(
                            this@MainActivity,
                            "Ошибка при загрузке новостей",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }
}