package app.worldofcinema.presentation.view.movies.fragments.allactors.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.databinding.ActorFullcastBinding
import app.worldofcinema.presentation.view.movies.fragments.detailsmovie.adapter.listener.ActorsListener
import app.worldofcinema.presentation.view.movies.model.detailsfragment.Actor
import com.squareup.picasso.Picasso

class AllActorViewHolder(
    private val viewBinding: ActorFullcastBinding,
    private var actorsListener: ActorsListener,
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(actor: Actor) {
        viewBinding.actorName.text = actor.name
        viewBinding.actorRole.text = actor.asCharacter

        Picasso.get()
            .load(Uri.parse(actor.image))
            .placeholder(R.drawable.ic_main_noimgfilm)
            .fit()
            .centerCrop()
            .into(viewBinding.actorImg)

        itemView.setOnClickListener {
            actorsListener.onActorSelected(actor.id)
        }
    }
}