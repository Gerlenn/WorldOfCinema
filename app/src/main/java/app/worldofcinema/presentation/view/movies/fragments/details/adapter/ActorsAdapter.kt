package app.worldofcinema.presentation.view.movies.fragments.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.ActorFilmBinding
import app.worldofcinema.presentation.view.movies.fragments.details.adapter.listener.ActorsListener
import app.worldofcinema.presentation.view.movies.model.detailsfragment.Actor

class ActorsAdapter(
    private var actorsListener: ActorsListener,
) : RecyclerView.Adapter<ActorsViewHolder>() {

    private var actorList = mutableListOf<Actor>()

    fun submitList(list: List<Actor>) {
        this.actorList.clear()
        this.actorList.addAll(list.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val viewBinding =
            ActorFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorsViewHolder(viewBinding, actorsListener)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(actorList[position])
    }

    override fun getItemCount(): Int {
        return actorList.size
    }
}