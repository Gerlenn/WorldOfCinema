package app.worldofcinema.presentation.view.movies.fragments.main.adapter

import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.MainRecyclerRowItemBinding

class MainRecyclerViewHolder(
    viewBinding: MainRecyclerRowItemBinding,
) : RecyclerView.ViewHolder(viewBinding.root) {

    var categoryTitle = viewBinding.catTitle
    var itemRecycler = viewBinding.catItemRecycler
}