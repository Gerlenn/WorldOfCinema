package app.worldofcinema.presentation.view.movies.fragments.detailsmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.databinding.ImageFilmBinding
import app.worldofcinema.presentation.view.movies.model.detailsfragment.LinkImg

class ImagesAdapter(
) : RecyclerView.Adapter<ImagesViewHolder>() {

    private var imagesList = mutableListOf<LinkImg>()

    fun submitList(list: List<LinkImg>) {
        this.imagesList.clear()
        this.imagesList.addAll(list.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val viewBinding =
            ImageFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(imagesList[position])
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }
}