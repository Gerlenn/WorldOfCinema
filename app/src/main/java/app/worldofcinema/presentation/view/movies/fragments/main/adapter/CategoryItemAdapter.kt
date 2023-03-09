package app.worldofcinema.presentation.view.movies.fragments.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.ItemFilmBinding
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener
import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel

class CategoryItemAdapter(
    private val categoryItem: List<MoviesModel>,
    private val movieListener: MovieListener,
) : RecyclerView.Adapter<CategoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val viewBinding =
            ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(viewBinding, movieListener)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(categoryItem[position])
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }
}