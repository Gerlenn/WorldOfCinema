package app.worldofcinema.presentation.view.movies.fragments.main.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener

class MainRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var categoryTitle: TextView
    var itemRecycler: RecyclerView

    init {
        categoryTitle = itemView.findViewById(R.id.cat_title)
        itemRecycler = itemView.findViewById(R.id.cat_item_recycler)
    }
}