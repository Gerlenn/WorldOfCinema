package app.worldofcinema.presentation.view.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.worldofcinema.R
import app.worldofcinema.presentation.view.movies.adapter.MainRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

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
        mainRecyclerAdapter = MainRecyclerAdapter(requireContext())
        minCategoryRecycler.adapter = mainRecyclerAdapter

        viewModel.getData()

        viewModel.items.observe(viewLifecycleOwner) { listItems ->
            mainRecyclerAdapter.submitList(listItems)
            Log.w("fragment", "${listItems}")
        }
    }
}