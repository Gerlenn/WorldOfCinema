package app.worldofcinema.presentation.view.movies.fragments.detailsactor.adapter

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.databinding.ActorMovieBinding
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener
import app.worldofcinema.presentation.view.movies.model.detailsactorfragment.KnownForModel
import com.squareup.picasso.Picasso

class ActorMovieViewHolder(
    private val viewBinding: ActorMovieBinding,
    private val movieListener: MovieListener,
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(knownForModel: KnownForModel) {

        viewBinding.actorTitleMovie.text = knownForModel.title

        if (knownForModel.year != null && !knownForModel.year.isEmpty()){
            viewBinding.actorMovieDate.text = knownForModel.year
        }else{
            viewBinding.actorMovieDate.visibility = View.GONE
        }

        viewBinding.actorRole.text = knownForModel.role

        Picasso.get()
            .load(Uri.parse(knownForModel.image))
            .fit()
            .centerCrop()
            .placeholder(R.drawable.ic_main_noimgfilm)
            .into(viewBinding.movieActorImage)

        itemView.setOnClickListener {
            movieListener.onMovieSelected(knownForModel.id)
        }
    }
}