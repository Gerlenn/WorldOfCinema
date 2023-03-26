package app.worldofcinema.presentation.view.movies.fragments.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.MainRecyclerRowItemBinding
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.CategoryMoviesListener
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener
import app.worldofcinema.presentation.view.movies.model.moviesfragment.Category
import app.worldofcinema.presentation.view.movies.model.moviesfragment.CategoryMovies
import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel

class MainRecyclerAdapter(
    private val context: Context,
    private val movieListener: MovieListener,
    private val categoryMoviesListener: CategoryMoviesListener,
) : RecyclerView.Adapter<MainRecyclerViewHolder>() {

    private var allCategoryList = mutableListOf<Category>()

    fun submitList(list: List<Category>) {
        this.allCategoryList.clear()
        this.allCategoryList.addAll(list.toMutableList())
        this.notifyDataSetChanged()
    }

    private fun setCatItemRecycler(recyclerView: RecyclerView, categoryItem: List<MoviesModel>) {
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = CategoryItemAdapter(categoryItem, movieListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val viewBinding =
            MainRecyclerRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainRecyclerViewHolder(viewBinding, categoryMoviesListener)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.categoryTitle.text = allCategoryList[position].Title
        setCatItemRecycler(holder.itemRecycler, allCategoryList[position].Items)
        holder.bind(allCategoryList[position])
    }

    override fun getItemCount(): Int {
        return allCategoryList.size
    }
}