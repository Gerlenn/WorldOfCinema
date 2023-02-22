package app.worldofcinema.presentation.view.movies.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R

class MainRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var categoryTitle: TextView
    var itemRecycler: RecyclerView

    init {
        categoryTitle = itemView.findViewById(R.id.cat_title)
        itemRecycler = itemView.findViewById(R.id.cat_item_recycler)
    }
}