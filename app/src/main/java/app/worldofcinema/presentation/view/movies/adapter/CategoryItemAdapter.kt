package app.worldofcinema.presentation.view.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.data.model.MovieModel

class CategoryItemAdapter(
    private val categoryItem: List<MovieModel>,
) : RecyclerView.Adapter<CategoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(categoryItem[position])
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }
}