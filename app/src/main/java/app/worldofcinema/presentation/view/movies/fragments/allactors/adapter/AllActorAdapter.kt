package app.worldofcinema.presentation.view.movies.fragments.allactors.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.ActorFullcastBinding
import app.worldofcinema.presentation.view.movies.fragments.detailsmovie.adapter.listener.ActorsListener
import app.worldofcinema.presentation.view.movies.model.detailsfragment.Actor

class AllActorAdapter(
    private var actorsListener: ActorsListener,
) : RecyclerView.Adapter<AllActorViewHolder>() {

    private var mainActorFromTheMovie = mutableListOf<Actor>()

    fun submitList(listActors: List<Actor>) {
        this.mainActorFromTheMovie.clear()
        this.mainActorFromTheMovie.addAll(listActors.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllActorViewHolder {
        val viewBinding =
            ActorFullcastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllActorViewHolder(viewBinding, actorsListener)
    }

    override fun onBindViewHolder(holder: AllActorViewHolder, position: Int) {
        holder.bind(mainActorFromTheMovie[position])
    }

    override fun getItemCount(): Int {
        return mainActorFromTheMovie.size
    }
}