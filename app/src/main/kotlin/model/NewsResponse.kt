package model

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val results: List<NewsArticle>
)
