package app.worldofcinema.presentation.view.movies.fragments.main.allmoviescategory.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.ItemFilmBinding
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener
import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel
import app.worldofcinema.utils.AppConstants
import com.squareup.picasso.Picasso

class MoviesInCategoryViewHolder(
    private val viewBinding: ItemFilmBinding,
    private val movieListener: MovieListener
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(moviesModel: MoviesModel) {
        viewBinding.titleMovie.text = moviesModel.title
        viewBinding.date.text = moviesModel.year

        Picasso.get().load(Uri.parse(moviesModel.image)).resize(130, 180)
            .into(viewBinding.movieImage)

        if (moviesModel.imDbRating != null && !moviesModel.imDbRating.isEmpty()) {
            viewBinding.imDbRating.text = "${AppConstants.RATING} ${moviesModel.imDbRating}"
        } else {
            viewBinding.imDbRating.text = ""
        }

        itemView.setOnClickListener {
            movieListener.onMovieSelected(moviesModel.id)
        }
    }
}