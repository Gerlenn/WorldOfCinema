package app.worldofcinema.presentation.view.movies.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.MainRecyclerAdapter
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener
import app.worldofcinema.utils.AppConstants.ID
import app.worldofcinema.utils.NavigationHelper.navigateWithBundleID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), MovieListener {

    private val viewModel: MoviesViewModel by viewModels()

    private lateinit var minCategoryRecycler: RecyclerView
    private lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        minCategoryRecycler = requireView().findViewById(R.id.main_recycler)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        minCategoryRecycler.layoutManager = layoutManager
        mainRecyclerAdapter = MainRecyclerAdapter(requireContext(), this)
        minCategoryRecycler.adapter = mainRecyclerAdapter

        viewModel.getData()

        viewModel.showAllMovies()

        viewModel.items.observe(viewLifecycleOwner) { listItems ->
            mainRecyclerAdapter.submitList(listItems)
        }

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.showId.observe(viewLifecycleOwner) { showId ->
            Toast.makeText(context, showId, Toast.LENGTH_SHORT).show()
        }

        viewModel.bundle.observe(viewLifecycleOwner) { navBundle ->
            if (navBundle != null) {
                val bundle = Bundle()
                bundle.putString(ID, navBundle.id)

                navigateWithBundleID(
                    navBundle.destinationId,
                    bundle
                )
                viewModel.userNavigated()
            }
        }
    }

    override fun onMovieSelected(id: String) {
        viewModel.onMovieSelected(id)
    }
}