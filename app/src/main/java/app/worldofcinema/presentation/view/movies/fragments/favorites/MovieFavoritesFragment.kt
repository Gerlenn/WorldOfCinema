package app.worldofcinema.presentation.view.movies.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.worldofcinema.R
import app.worldofcinema.databinding.FragmentMovieFavoritesBinding
import app.worldofcinema.presentation.view.movies.fragments.favorites.adapter.FavoritesAdapter
import app.worldofcinema.presentation.view.movies.fragments.favorites.adapter.listener.FavoritesListener
import app.worldofcinema.utils.AppConstants
import app.worldofcinema.utils.NavigationHelper.navigateWithBundleID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFavoritesFragment : Fragment(), FavoritesListener {

    private val viewModel: MovieFavoritesViewModel by viewModels()

    private var _viewBinding: FragmentMovieFavoritesBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var movieFavoritesAdapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentMovieFavoritesBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieFavoritesAdapter = FavoritesAdapter(this)
        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = movieFavoritesAdapter

        viewModel.getFavorites()

        viewModel.favoritesMovies.observe(viewLifecycleOwner) {
            movieFavoritesAdapter.submitList(it)
        }

        viewModel.errorGetFav.observe(viewLifecycleOwner) { errorGetMsg ->
            Toast.makeText(context, getString(errorGetMsg), Toast.LENGTH_SHORT).show()
        }

        viewModel.errorDelFav.observe(viewLifecycleOwner) { errorDellMsg ->
            Toast.makeText(context, getString(errorDellMsg), Toast.LENGTH_SHORT).show()
        }

        viewModel.logoutUser.observe(viewLifecycleOwner){ logOut ->
            if (logOut != null) {
                val navGraph = findNavController().navInflater.inflate(logOut)
                navGraph.startDestination = R.id.loginFragment
                findNavController().graph = navGraph
            }
        }

        viewBinding.logOut.setOnClickListener {
            viewModel.logOutUser()
        }

        viewModel.bundle.observe(viewLifecycleOwner) { navBundle ->
            if (navBundle != null) {
                val bundle = Bundle()
                bundle.putString(AppConstants.ID, navBundle.id)

                navigateWithBundleID(
                    navBundle.destinationId,
                    bundle
                )
                viewModel.userNavigated()
            }
        }
    }

    override fun onDeleteClicked(id: String) {
        viewModel.deleteFavorite(id)
    }

    override fun onMovieSelected(id: String) {
        viewModel.onMovieSelected(id)
    }
}