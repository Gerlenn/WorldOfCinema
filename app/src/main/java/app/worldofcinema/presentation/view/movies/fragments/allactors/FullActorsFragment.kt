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
import app.worldofcinema.utils.BundleConstants.MOVIE_ID
import app.worldofcinema.utils.BundleConstants.MOVIE_TITLE
import app.worldofcinema.utils.NavigationHelper.navigateWithBundleID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullActorsFragment : Fragment() {

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

        allActorAdapter = AllActorAdapter()
        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = allActorAdapter

        viewModel.listActors.observe(viewLifecycleOwner) { listActors ->
            allActorAdapter.submitList(listActors)
        }

        viewModel.error.observe(viewLifecycleOwner) { getAllActorsMovieError ->
            Toast.makeText(context, getString(getAllActorsMovieError), Toast.LENGTH_SHORT).show()
        }

        viewModel.bundle.observe(viewLifecycleOwner) { navBundle ->
            if (navBundle != null) {
                val bundle = Bundle()
                bundle.putString(MOVIE_ID, navBundle.movieId)
                bundle.putString(MOVIE_TITLE, navBundle.movieTitle)

                navigateWithBundleID(navBundle.destinationId, bundle)
                viewModel.userNavigated()
            }
        }

        val bundle = arguments
        bundle?.let { safeBundle ->
            val movieId = safeBundle.getString(MOVIE_ID)
            val movieTitle = safeBundle.getString(MOVIE_TITLE)
            viewBinding.movieTitle.text = movieTitle
            movieId?.let { movieID ->
                viewModel.showActorsMovies(movieID)
            }
        }
    }
}