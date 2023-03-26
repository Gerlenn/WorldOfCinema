package app.worldofcinema.presentation.view.movies.fragments.main.allmoviescategory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.ItemFilmBinding
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener
import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel

class MoviesInCategoryAdapter(
    private val movieListener: MovieListener
) : RecyclerView.Adapter<MoviesInCategoryViewHolder>() {

    private var moviesInCategory = mutableListOf<MoviesModel>()

    fun submitList(list: List<MoviesModel>) {
        this.moviesInCategory.clear()
        this.moviesInCategory.addAll(list.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesInCategoryViewHolder {
        val viewBinding =
            ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesInCategoryViewHolder(viewBinding, movieListener)
    }

    override fun onBindViewHolder(holder: MoviesInCategoryViewHolder, position: Int) {
        holder.bind(moviesInCategory[position])
    }

    override fun getItemCount(): Int {
        return moviesInCategory.size
    }
}