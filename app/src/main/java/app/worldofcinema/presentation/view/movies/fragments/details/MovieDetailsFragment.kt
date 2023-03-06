package app.worldofcinema.presentation.view.movies.fragments.details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.worldofcinema.databinding.FragmentMovieDetailsBinding
import app.worldofcinema.utils.AppConstants.ID
import app.worldofcinema.utils.AppConstants.RATING
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var webView: WebView

    private val viewModel: MovieDetailsViewModel by viewModels()

    private var _viewBinding: FragmentMovieDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentMovieDetailsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        bundle?.let { safeBundle ->
            val id = safeBundle.getString(ID)
            id?.let { viewModel.getMovieDetailsById(it) }
        }

        viewModel.errorGetMovieDetails.observe(viewLifecycleOwner) { errGetDetails ->
            Toast.makeText(context, getString(errGetDetails), Toast.LENGTH_SHORT).show()
        }

        viewModel.errorFavSelected.observe(viewLifecycleOwner) { errFavSelected ->
            Toast.makeText(context, getString(errFavSelected), Toast.LENGTH_SHORT).show()
        }

        viewModel.movie.observe(viewLifecycleOwner) { detailsModel ->
            webView = viewBinding.detTrailer
            webView.webViewClient = WebViewClient()
            webView.loadUrl(detailsModel.linkEmbed.toString())
            viewBinding.detTitle.text = detailsModel.title
            viewBinding.detYear.text = detailsModel.year
            viewBinding.detCountries.text = detailsModel.countries
            viewBinding.detGenres.text = detailsModel.genres
            viewBinding.detImDbRating.text = "$RATING ${detailsModel.imDbRating}"
            viewBinding.detPlot.text = detailsModel.plot
            viewBinding.detAwards.text = detailsModel.awards
            viewBinding.detStars.text = detailsModel.stars
            Picasso.get().load(Uri.parse(detailsModel.image)).into(viewBinding.detImage)
            Picasso.get().load(Uri.parse(detailsModel.thumbnailUrl))
                .resize(308, 171).into(viewBinding.detImageTrailer)

            viewBinding.btnFavorite.setOnClickListener {
                viewBinding.btnFavorite.isSelected = !it.isSelected
                viewModel.favoriteSelected(detailsModel.id, true)
            }
        }

        viewBinding.detImageTrailer.setOnClickListener {
            viewBinding.detImageTrailer.visibility = View.GONE
        }
    }
}