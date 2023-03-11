package app.worldofcinema.presentation.view.movies.fragments.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.SearchFilmBinding
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener
import app.worldofcinema.presentation.view.movies.fragments.search.adapter.listener.SearchListener
import app.worldofcinema.presentation.view.movies.model.searchfragment.ResultSearchModel
import app.worldofcinema.presentation.view.movies.model.searchfragment.SearchModel

class SearchAdapter(
    private val searchListener: SearchListener,
) : RecyclerView.Adapter<SearchViewHolder>() {

    private var searchMovies = mutableListOf<ResultSearchModel>()

    fun submitList(list: List<ResultSearchModel>) {
        this.searchMovies.clear()
        this.searchMovies.addAll(list.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val viewBinding = SearchFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(viewBinding, searchListener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchMovies[position])
    }

    override fun getItemCount(): Int {
        return searchMovies.size
    }
}