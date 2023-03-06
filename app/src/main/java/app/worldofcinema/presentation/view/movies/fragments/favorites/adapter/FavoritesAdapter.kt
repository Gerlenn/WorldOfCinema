package app.worldofcinema.presentation.view.movies.fragments.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.FavoritesFilmBinding
import app.worldofcinema.presentation.view.movies.fragments.favorites.adapter.listener.FavoritesListener
import app.worldofcinema.presentation.view.movies.model.favoritesfragment.FavoritesModel

class FavoritesAdapter(
    private var favoritesListener: FavoritesListener,
) : RecyclerView.Adapter<FavoritesViewHolder>() {

    private var searchMovies = mutableListOf<FavoritesModel>()

    fun submitList(list: List<FavoritesModel>) {
        this.searchMovies.clear()
        this.searchMovies.addAll(list.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val viewBinding =
            FavoritesFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(viewBinding, favoritesListener)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(searchMovies[position])
    }

    override fun getItemCount(): Int {
        return searchMovies.size
    }
}