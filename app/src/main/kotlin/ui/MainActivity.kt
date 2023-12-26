package ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import adapter.NewsAdapter
import data.NewsApiClient
import com.example.lab3v2.R

class MainActivity : AppCompatActivity() {

    private lateinit var keywordEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        keywordEditText = findViewById(R.id.etKeyword)
        searchButton = findViewById(R.id.btnSearch)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchButton.setOnClickListener {
            val keyword = keywordEditText.text.toString()
            if (keyword.isNotEmpty()) {
                searchNews(keyword)
            }
        }
    }

    private fun searchNews(keyword: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            val response = NewsApiClient.apiService.getNews("pub_3536394fa89922df81eef3ee5f8e5fc0c56da", keyword)
            if (response.status == "success") {
                val newsList = response.results.take(10)
                val adapter = NewsAdapter(newsList)
                recyclerView.adapter = adapter
            }
        }
    }
}