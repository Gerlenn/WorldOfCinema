package app.worldofcinema.presentation.view.movies.fragments.detailsactor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.ActorMovieBinding
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener
import app.worldofcinema.presentation.view.movies.model.detailsactorfragment.KnownForModel
import app.worldofcinema.presentation.view.movies.model.detailsfragment.Actor

class ActorMovieAdapter(
    private val movieListener: MovieListener,
) : RecyclerView.Adapter<ActorMovieViewHolder>() {

    private var actorMovieList = mutableListOf<KnownForModel>()

    fun submitList(list: List<KnownForModel>) {
        this.actorMovieList.clear()
        this.actorMovieList.addAll(list.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorMovieViewHolder {
        val viewBinding =
            ActorMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorMovieViewHolder(viewBinding, movieListener)
    }

    override fun onBindViewHolder(holder: ActorMovieViewHolder, position: Int) {
        holder.bind(actorMovieList[position])
    }

    override fun getItemCount(): Int {
        return actorMovieList.size
    }
}