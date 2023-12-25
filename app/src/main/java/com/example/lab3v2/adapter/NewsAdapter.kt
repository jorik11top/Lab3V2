import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3v2.R  // Проверьте, что здесь правильный путь к ресурсам R
import com.example.lab3v2.model.News

class NewsAdapter(private var newsList: List<News>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = newsList[position]
        holder.titleTextView.text = currentNews.title
        holder.descriptionTextView.text = currentNews.description
    }

    override fun getItemCount() = newsList.size

    // Добавим метод для обновления данных
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<News>) {
        newsList = newList
        notifyDataSetChanged()
    }
}