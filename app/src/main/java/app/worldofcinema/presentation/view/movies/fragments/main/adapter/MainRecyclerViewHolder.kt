package app.worldofcinema.presentation.view.movies.fragments.main.adapter

import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.MainRecyclerRowItemBinding
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.CategoryMoviesListener
import app.worldofcinema.presentation.view.movies.model.moviesfragment.Category
import app.worldofcinema.presentation.view.movies.model.moviesfragment.CategoryMovies
import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel

class MainRecyclerViewHolder(
    private val viewBinding: MainRecyclerRowItemBinding,
    private val categoryMoviesListener: CategoryMoviesListener
) : RecyclerView.ViewHolder(viewBinding.root) {

    var categoryTitle = viewBinding.catTitle
    var itemRecycler = viewBinding.catItemRecycler

    fun bind(category: Category) {
        viewBinding.btnShowAllMovies.setOnClickListener {
            categoryMoviesListener.onCategorySelected(category.Title)
        }
    }
}