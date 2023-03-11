package app.worldofcinema.presentation.view.movies.fragments.favorites.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.FavoritesFilmBinding
import app.worldofcinema.presentation.view.movies.fragments.favorites.adapter.listener.FavoritesListener
import app.worldofcinema.presentation.view.movies.model.favoritesfragment.FavoritesModel
import app.worldofcinema.utils.AppConstants.RATING
import com.squareup.picasso.Picasso

class FavoritesViewHolder(
    private val viewBinding: FavoritesFilmBinding,
    private var favoritesListener: FavoritesListener,
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(favoritesModel: FavoritesModel) {
        viewBinding.favoriteDate.text = favoritesModel.year
        viewBinding.favoriteImDbRating.text = favoritesModel.imDbRating
        viewBinding.favoriteTitleMovie.text = favoritesModel.title
        Picasso.get().load(Uri.parse(favoritesModel.image)).into(viewBinding.favoriteMovieImage)

        if (favoritesModel.imDbRating != null && !favoritesModel.imDbRating.isEmpty()) {
            viewBinding.favoriteImDbRating.text = "$RATING ${viewBinding.favoriteImDbRating.text}"
        } else {
            viewBinding.favoriteImDbRating.text = ""
        }

        viewBinding.favoriteDelete.setOnClickListener {
            favoritesListener.onDeleteClicked(favoritesModel.id)
        }

        itemView.setOnClickListener {
            favoritesListener.onMovieSelected(favoritesModel.id)
        }
    }
}