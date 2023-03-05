package app.worldofcinema.presentation.view.movies.fragments.search.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.SearchFilmBinding
import app.worldofcinema.presentation.view.movies.model.searchfragment.ResultSearchModel
import com.squareup.picasso.Picasso

class SearchViewHolder(
    private val viewBinding: SearchFilmBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(resultSearchModel: ResultSearchModel) {
        viewBinding.searchTitleMovie.text = resultSearchModel.title
        viewBinding.searchMovieDate.text = resultSearchModel.description
        Picasso.get().load(Uri.parse(resultSearchModel.image)).into(viewBinding.searchMovieImage)
    }
}
