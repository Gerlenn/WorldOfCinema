package app.worldofcinema.presentation.view.movies.fragments.main.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.ItemFilmBinding
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener
import app.worldofcinema.presentation.view.movies.model.moviesfragment.MoviesModel
import app.worldofcinema.utils.AppConstants.RATING
import com.squareup.picasso.Picasso

class CategoryItemViewHolder(
    private val viewBinding: ItemFilmBinding,
    private val movieListener: MovieListener,
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(moviesModel: MoviesModel) {
        viewBinding.titleMovie.text = moviesModel.title
        viewBinding.date.text = moviesModel.year
        viewBinding.imDbRating.text = moviesModel.imDbRating

        Picasso.get().load(Uri.parse(moviesModel.image)).resize(130, 180)
            .into(viewBinding.movieImage)

        if (moviesModel.imDbRating != null && !moviesModel.imDbRating.isEmpty()) {
            viewBinding.imDbRating.text = "$RATING ${moviesModel.imDbRating}"
        } else {
            viewBinding.imDbRating.text = ""
        }

        itemView.setOnClickListener {
            movieListener.onMovieSelected(moviesModel.id)
        }
    }
}