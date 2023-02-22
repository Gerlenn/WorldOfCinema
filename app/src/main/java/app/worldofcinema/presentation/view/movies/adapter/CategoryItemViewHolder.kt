package app.worldofcinema.presentation.view.movies.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.data.model.MovieModel
import app.worldofcinema.utils.AppConstants.RATING
import com.squareup.picasso.Picasso

class CategoryItemViewHolder(
    private val itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: MovieModel) {

        val movieImage = itemView.findViewById<ImageView>(R.id.movieImage)
        val titleMovie = itemView.findViewById<TextView>(R.id.titleMovie)
        val date = itemView.findViewById<TextView>(R.id.date)
        val imDbRating = itemView.findViewById<TextView>(R.id.imDbRating)

        Picasso.get().load(Uri.parse(item.image)).resize(130, 180).into(movieImage)
        titleMovie.text = item.title
        date.text = item.year

        if (item.imDbRating != null) {
            imDbRating.text = "$RATING ${item.imDbRating}"
        } else {
            imDbRating.text = ""
        }
    }
}
