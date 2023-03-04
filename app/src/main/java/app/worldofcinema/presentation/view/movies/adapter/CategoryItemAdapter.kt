package app.worldofcinema.presentation.view.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.presentation.view.movies.adapter.listener.MovieListener
import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel

class CategoryItemAdapter(
    private val categoryItem: List<MoviesModel>,
    private val movieListener: MovieListener
) : RecyclerView.Adapter<CategoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return CategoryItemViewHolder(view, movieListener)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(categoryItem[position])
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }
}