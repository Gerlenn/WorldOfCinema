package app.worldofcinema.presentation.view.movies.fragments.allactors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import app.worldofcinema.databinding.FragmentFullActorsBinding
import app.worldofcinema.presentation.view.movies.fragments.allactors.adapter.AllActorAdapter
import app.worldofcinema.presentation.view.movies.fragments.detailsmovie.adapter.listener.ActorsListener
import app.worldofcinema.utils.AppConstants
import app.worldofcinema.utils.BundleConstants.MOVIE_ID
import app.worldofcinema.utils.BundleConstants.MOVIE_TITLE
import app.worldofcinema.utils.NavigationHelper.navigateWithBundleID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullActorsFragment : Fragment(), ActorsListener {

    private val viewModel: FullActorsFragmentViewModel by viewModels()

    private var _viewBinding: FragmentFullActorsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var allActorAdapter: AllActorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentFullActorsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allActorAdapter = AllActorAdapter(this)
        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = allActorAdapter

        viewModel.listActors.observe(viewLifecycleOwner) { listActors ->
            allActorAdapter.submitList(listActors)
        }

        viewModel.error.observe(viewLifecycleOwner) { getAllActorsMovieError ->
            Toast.makeText(context, getString(getAllActorsMovieError), Toast.LENGTH_SHORT).show()
        }

        val bundleMovie = arguments
        bundleMovie?.let { safeBundle ->
            val movieId = safeBundle.getString(MOVIE_ID)
            val movieTitle = safeBundle.getString(MOVIE_TITLE)
            viewBinding.movieTitle.text = movieTitle
            movieId?.let { movieID ->
                viewModel.showActorsMovies(movieID)
            }
        }

        viewModel.bundleMovie.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                val bundleMovie = Bundle()
                bundleMovie.putString(MOVIE_ID, movie.movieId)
                bundleMovie.putString(MOVIE_TITLE, movie.movieTitle)

                navigateWithBundleID(movie.destinationId, bundleMovie)
                viewModel.userNavigated()
            }
        }

        viewModel.bundleActor.observe(viewLifecycleOwner) { actor ->
            if (actor != null) {
                val bundleActor = Bundle()
                bundleActor.putString(AppConstants.ID, actor.id)

                navigateWithBundleID(actor.destinationId, bundleActor)
                viewModel.userNavigated()
            }
        }
    }

    override fun onActorSelected(actorId: String) {
        viewModel.onActorSelected(actorId)
    }
}