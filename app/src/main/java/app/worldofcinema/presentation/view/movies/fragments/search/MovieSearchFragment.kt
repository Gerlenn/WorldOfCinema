package app.worldofcinema.presentation.view.movies.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import app.worldofcinema.databinding.FragmentMovieSearchBinding
import app.worldofcinema.presentation.view.movies.fragments.search.adapter.SearchAdapter
import app.worldofcinema.presentation.view.movies.fragments.search.adapter.listener.SearchListener
import app.worldofcinema.utils.AppConstants
import app.worldofcinema.utils.NavigationHelper.navigateWithBundleID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSearchFragment : Fragment(), SearchListener {

    private val viewModel: MovieSearchViewModel by viewModels()

    private var _viewBinding: FragmentMovieSearchBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var movieSearchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentMovieSearchBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieSearchAdapter = SearchAdapter(this)
        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = movieSearchAdapter

        viewModel.movies.observe(viewLifecycleOwner) { listItems ->
            movieSearchAdapter.submitList(listItems)
        }

        viewModel.shortSearch.observe(viewLifecycleOwner) { shortInput ->
            Toast.makeText(context, getString(shortInput), Toast.LENGTH_SHORT).show()
        }

        viewModel.error.observe(viewLifecycleOwner) { searchError ->
            Toast.makeText(context, getString(searchError), Toast.LENGTH_SHORT).show()
        }

        viewModel.bundle.observe(viewLifecycleOwner) { navBundle ->
            if (navBundle != null) {
                val bundle = Bundle()
                bundle.putString(AppConstants.ID, navBundle.id)

                navigateWithBundleID(navBundle.destinationId, bundle)
                viewModel.userNavigated()
            }
        }

        viewBinding.searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String?): Boolean {
                viewModel.findIMovies(searchText ?: "")
                return false
            }
        })
    }

    override fun onMovieSelected(id: String) {
        viewModel.onMovieSelected(id)
    }
}