package app.worldofcinema.presentation.view.movies.fragments.search.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.databinding.SearchFilmBinding
import app.worldofcinema.presentation.view.movies.fragments.search.adapter.listener.SearchListener
import app.worldofcinema.presentation.view.movies.model.searchfragment.ResultSearchModel
import com.squareup.picasso.Picasso

class SearchViewHolder(
    private val viewBinding: SearchFilmBinding,
    private val searchListener: SearchListener,
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(resultSearchModel: ResultSearchModel) {
        viewBinding.searchTitleMovie.text = resultSearchModel.title
        viewBinding.searchMovieDate.text = resultSearchModel.description
        Picasso.get()
            .load(Uri.parse(resultSearchModel.image))
            .fit()
            .centerCrop()
            .placeholder(R.drawable.ic_main_noimgfilm)
            .into(viewBinding.searchMovieImage)

        itemView.setOnClickListener {
            searchListener.onMovieSelected(resultSearchModel.id)
        }
    }
}
