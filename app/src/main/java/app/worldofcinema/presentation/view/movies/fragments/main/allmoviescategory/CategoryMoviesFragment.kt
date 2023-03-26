package app.worldofcinema.presentation.view.movies.fragments.main.allmoviescategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import app.worldofcinema.databinding.FragmentCategoryMoviesBinding
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener
import app.worldofcinema.presentation.view.movies.fragments.main.allmoviescategory.adapter.MoviesInCategoryAdapter
import app.worldofcinema.utils.AppConstants
import app.worldofcinema.utils.NavigationHelper.navigateWithBundleID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryMoviesFragment : Fragment(), MovieListener {

    private val viewModel: CategoryMoviesViewModel by viewModels()

    private var _viewBinding: FragmentCategoryMoviesBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var moviesInCategoryAdapter: MoviesInCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentCategoryMoviesBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesInCategoryAdapter = MoviesInCategoryAdapter(this)
        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = moviesInCategoryAdapter

        viewModel.listMovie.observe(viewLifecycleOwner) { listMovie ->
            moviesInCategoryAdapter.submitList(listMovie)
        }

        viewModel.error.observe(viewLifecycleOwner) { getCategoryMoviesError ->
            Toast.makeText(context, getString(getCategoryMoviesError), Toast.LENGTH_SHORT).show()
        }

        viewModel.bundle.observe(viewLifecycleOwner) { navBundle ->
            if (navBundle != null) {
                val bundle = Bundle()
                bundle.putString(AppConstants.ID, navBundle.id)

                navigateWithBundleID(navBundle.destinationId, bundle)
                viewModel.userNavigated()
            }
        }

        val bundle = arguments
        bundle?.let { safeBundle ->
            val title = safeBundle.getString(AppConstants.CATEGORY_TITLE)
            title?.let {
                viewModel.showCategoryMovies(it)
                viewBinding.catTitle.text = title
            }
        }
    }

    override fun onMovieSelected(id: String) {
        viewModel.onMovieSelected(id)
    }
}