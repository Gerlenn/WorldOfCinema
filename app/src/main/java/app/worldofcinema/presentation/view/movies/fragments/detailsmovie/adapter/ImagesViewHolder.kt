package app.worldofcinema.presentation.view.movies.fragments.detailsmovie.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.databinding.ImageFilmBinding
import app.worldofcinema.presentation.view.movies.model.detailsfragment.LinkImg
import com.squareup.picasso.Picasso

class ImagesViewHolder(
    private val viewBinding: ImageFilmBinding,
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(linkImg: LinkImg) {
        Picasso.get().load(Uri.parse(linkImg.image))
            .resize(500, 400)
            .centerInside()
            .placeholder(R.drawable.ic_details_noimg)
            .priority(Picasso.Priority.HIGH)
            .into(viewBinding.filmImg)
    }
}