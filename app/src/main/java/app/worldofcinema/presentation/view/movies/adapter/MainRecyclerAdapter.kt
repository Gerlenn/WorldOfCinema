package app.worldofcinema.presentation.view.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.data.model.MovieModel
import app.worldofcinema.presentation.view.movies.model.Category

class MainRecyclerAdapter(
    private val context: Context,
) : RecyclerView.Adapter<MainRecyclerViewHolder>() {

    private var allCategoryList = mutableListOf<Category>()

    fun submitList(list: List<Category>) {
        this.allCategoryList.clear()
        this.allCategoryList.addAll(list.toMutableList())
        this.notifyDataSetChanged()
    }

    private fun setCatItemRecycler(recyclerView: RecyclerView, categoryItem: List<MovieModel>) {
        val movies: List<MovieModel> =
            if (categoryItem.size > 10) {
                categoryItem.subList(0, 10)
            } else {
                categoryItem
            }
        val itemRecyclerAdapter = CategoryItemAdapter(movies)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = itemRecyclerAdapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        return MainRecyclerViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.main_recycler_row_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.categoryTitle.text = allCategoryList[position].Title
        setCatItemRecycler(holder.itemRecycler, allCategoryList[position].Items)
    }

    override fun getItemCount(): Int {
        return allCategoryList.size
    }
}