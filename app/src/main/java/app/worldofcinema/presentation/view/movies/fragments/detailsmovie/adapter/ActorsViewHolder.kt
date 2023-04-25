package app.worldofcinema.presentation.view.movies.fragments.detailsmovie.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.databinding.ActorFilmBinding
import app.worldofcinema.presentation.view.movies.fragments.detailsmovie.adapter.listener.ActorsListener
import app.worldofcinema.presentation.view.movies.model.detailsfragment.Actor
import com.squareup.picasso.Picasso

class ActorsViewHolder(
    private val viewBinding: ActorFilmBinding,
    private var actorsListener: ActorsListener,
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(actor: Actor) {
        viewBinding.detActorName.text = actor.name
        Picasso.get()
            .load(Uri.parse(actor.image))
            .fit()
            .centerCrop()
            .placeholder(R.drawable.ic_main_noimgfilm)
            .into(viewBinding.actorImg)

        itemView.setOnClickListener {
            actorsListener.onActorSelected(actor.id)
        }
    }
}