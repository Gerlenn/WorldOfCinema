package app.worldofcinema.presentation.view.movies.fragments.details

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.worldofcinema.R
import app.worldofcinema.databinding.FragmentMovieDetailsBinding
import app.worldofcinema.utils.AppConstants
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

        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(context, getString(errorMsg), Toast.LENGTH_SHORT).show()
        }

        viewModel.movie.observe(viewLifecycleOwner) { detailsModel ->
            webView = viewBinding.detTrailer
            webView.webViewClient = WebViewClient()
            webView.loadUrl(detailsModel.linkEmbed.toString())
            webView.settings.loadWithOverviewMode = true
            webView.settings.useWideViewPort = true
            webView.settings.javaScriptEnabled = true

            val videoId = detailsModel.linkEmbed
            val html =
                "<html><body><div style=\"width: 88%; margin: 0 auto;\"><iframe frameborder=\"no\" width=\"100%\" height=\"100%\" src=\"$videoId\"></iframe></div></body></html>"
            webView.loadData(html, "text/html", "utf-8")

            viewBinding.detTitle.text = detailsModel.title
            viewBinding.detYear.text = detailsModel.year
            viewBinding.detCountries.text = detailsModel.countries
            viewBinding.detGenres.text = detailsModel.genres

            if (detailsModel.imDbRating != null && !detailsModel.imDbRating.isEmpty()) {
                viewBinding.detImDbRating.text = "$RATING ${detailsModel.imDbRating}"
            } else {
                viewBinding.detImDbRating.text = ""
            }

            viewBinding.detPlot.text = detailsModel.plot

            if (detailsModel.awards != null && !detailsModel.awards.isEmpty()) {
                viewBinding.detAwardsText.text = getString(R.string.awards)
                viewBinding.detAwards.text = "${detailsModel.awards}"
            } else {
                viewBinding.detAwardsText.visibility = View.GONE
                viewBinding.detAwards.visibility = View.GONE
            }

            viewBinding.detStars.text = detailsModel.stars
            Picasso.get().load(Uri.parse(detailsModel.image)).into(viewBinding.detImage)
            Picasso.get().load(Uri.parse(detailsModel.thumbnailUrl)).resize(308, 171)
                .into(viewBinding.detImageTrailer)

            val stateFavorit = detailsModel.isFavorite!!
//            viewBinding.btnFavorite.isSelected = detailsModel.isFavorite!!

            viewBinding.btnFavorite.setOnClickListener {
                viewBinding.btnFavorite.isSelected = !stateFavorit
                viewModel.favoriteSelected(detailsModel.id, !it.isSelected)
            }
        }

        viewBinding.detImageTrailer.setOnClickListener {
            viewBinding.detImageTrailer.visibility = View.GONE
        }
    }
}
