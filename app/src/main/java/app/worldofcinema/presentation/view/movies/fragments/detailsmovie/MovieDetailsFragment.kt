package app.worldofcinema.presentation.view.movies.fragments.detailsmovie

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
import androidx.recyclerview.widget.LinearLayoutManager
import app.worldofcinema.R
import app.worldofcinema.databinding.FragmentMovieDetailsBinding
import app.worldofcinema.presentation.view.movies.fragments.detailsmovie.adapter.ActorsAdapter
import app.worldofcinema.presentation.view.movies.fragments.detailsmovie.adapter.ImagesAdapter
import app.worldofcinema.presentation.view.movies.fragments.detailsmovie.adapter.listener.ActorsListener
import app.worldofcinema.utils.AppConstants.ID
import app.worldofcinema.utils.AppConstants.RATING
import app.worldofcinema.utils.BundleConstants.MOVIE_ID
import app.worldofcinema.utils.BundleConstants.MOVIE_TITLE
import app.worldofcinema.utils.NavigationHelper.navigateWithBundleID
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

const val DESCRIPTION = "Description"
const val STARS = "Stars"
const val MAIN_CAST = "Main cast"
const val IMAGES = "Images"
const val AWARDS = "Awards"

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), ActorsListener {

    private lateinit var webView: WebView

    private val viewModel: MovieDetailsViewModel by viewModels()

    private var _viewBinding: FragmentMovieDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var actorsAdapter: ActorsAdapter
    private lateinit var imagesAdapter: ImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentMovieDetailsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actorsAdapter = ActorsAdapter(this)
        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = actorsAdapter

        imagesAdapter = ImagesAdapter()
        val imagesRecyclerView = viewBinding.recyclerViewImages
        imagesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        imagesRecyclerView.adapter = imagesAdapter

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
            val html = "<html><body><div style=\"width: 88%; margin: 0 auto;\">" +
                    "<iframe frameborder=\"no\" width=\"100%\" height=\"100%\" src=\"$videoId\"></iframe>" +
                    "</div></body></html>"

            webView.loadData(html, "text/html", "utf-8")

            viewBinding.detDescription.text = DESCRIPTION
            viewBinding.detStarsText.text = STARS
            viewBinding.detMainCastText.text = MAIN_CAST
            viewBinding.imgText.text = IMAGES

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
                viewBinding.detAwardsText.text = AWARDS
                viewBinding.detAwards.text = "${detailsModel.awards}"
            } else {
                viewBinding.detAwardsText.visibility = View.GONE
                viewBinding.detAwards.visibility = View.GONE
            }

            viewBinding.detStars.text = detailsModel.stars

            Picasso.get()
                .load(Uri.parse(detailsModel.image))
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_main_noimgfilm)
                .priority(Picasso.Priority.HIGH)
                .into(viewBinding.detImage)

            Picasso.get()
                .load(Uri.parse(detailsModel.thumbnailUrl))
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_details_noimg)
                .into(viewBinding.detImageTrailer)

            actorsAdapter.submitList(detailsModel.actor)

            detailsModel.items?.let { imagesAdapter.submitList(it) }

            val stateFavorit = detailsModel.isFavorite!!
//            viewBinding.btnFavorite.isSelected = detailsModel.isFavorite!!

            viewBinding.btnFavorite.setOnClickListener {
                viewBinding.btnFavorite.isSelected = !stateFavorit
                viewModel.favoriteSelected(detailsModel.id, !it.isSelected)
            }

            viewBinding.allActors.setOnClickListener {
                viewModel.onAllActorSelected(detailsModel.id, detailsModel.title)
            }
        }

        viewModel.navigate.observe(viewLifecycleOwner) { navigate ->
            if (navigate != null) {
                val bundle = Bundle()
                bundle.putString(MOVIE_ID, navigate.movieId)
                bundle.putString(MOVIE_TITLE, navigate.movieTitle)


                navigateWithBundleID(
                    navigate.destinationId,
                    bundle
                )
                viewModel.userNavigatedWithActorParam()
            }
        }

        viewBinding.detImageTrailer.setOnClickListener {
            viewBinding.detImageTrailer.visibility = View.GONE
        }

        viewModel.bundleActor.observe(viewLifecycleOwner) { navBundle ->
            if (navBundle != null) {
                val bundle = Bundle()
                bundle.putString(ID, navBundle.id)

                navigateWithBundleID(navBundle.destinationId, bundle)
                viewModel.userNavigated()
            }
        }
    }

    override fun onActorSelected(actorId: String) {
        viewModel.onActorSelected(actorId)
    }
}

