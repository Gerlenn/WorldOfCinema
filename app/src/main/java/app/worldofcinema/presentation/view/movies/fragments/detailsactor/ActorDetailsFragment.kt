package app.worldofcinema.presentation.view.movies.fragments.detailsactor

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import app.worldofcinema.R
import app.worldofcinema.databinding.FragmentActorDetailsBinding
import app.worldofcinema.presentation.view.movies.fragments.detailsactor.adapter.ActorMovieAdapter
import app.worldofcinema.presentation.view.movies.fragments.main.adapter.listener.MovieListener
import app.worldofcinema.utils.AppConstants.ID
import app.worldofcinema.utils.NavigationHelper.navigateWithBundleID
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

const val SUMMARY = "Summary"
const val AWARDS = "Awards"
const val KNOW_FOR = "Known For"
const val DATE_OF_BIRTH = "Date of birth:"
const val DATE_OF_DEATH = "Date of death:"

@AndroidEntryPoint
class ActorDetailsFragment : Fragment(), MovieListener {

    private val viewModel: ActorDetailsViewModel by viewModels()

    private var _viewBinding: FragmentActorDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var actorMovieAdapter: ActorMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentActorDetailsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        actorMovieAdapter = ActorMovieAdapter(this)
        val actorMovieRecyclerView = viewBinding.recyclerView
        actorMovieRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        actorMovieRecyclerView.adapter = actorMovieAdapter

        val bundle = arguments
        bundle?.let { safeBundle ->
            val actorId = safeBundle.getString(ID)
            actorId?.let {
                viewModel.getActorDetailsById(actorId)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(context, getString(errorMsg), Toast.LENGTH_SHORT).show()
        }

        viewModel.actor.observe(viewLifecycleOwner) { actor ->

            if (actor.awards != null && !actor.awards.isEmpty()) {
                viewBinding.actorAwards.text = actor.awards
            } else {
                viewBinding.actorAwards.visibility = View.GONE
            }

            if (actor.birthDate != null && !actor.birthDate.isEmpty()) {
                viewBinding.actorBirthdateText.text = DATE_OF_BIRTH
                viewBinding.actorBirthdate.text = actor.birthDate
            } else {
                viewBinding.actorBirthdate.visibility = View.GONE
            }

            if (actor.deathDate != null && !actor.deathDate.isEmpty()) {
                viewBinding.actorDeathDateText.text = DATE_OF_DEATH
                viewBinding.actorDeathDate.text = actor.deathDate
            } else {
                viewBinding.actorDeathDate.visibility = View.GONE
            }

            actor.knownFor?.let { listActorMovies ->
                actorMovieAdapter.submitList(listActorMovies)
            }

            viewBinding.actorSummaryText.text = SUMMARY
            viewBinding.actorAwardsText.text = AWARDS
            viewBinding.actorKnownForText.text = KNOW_FOR

            viewBinding.actorName.text = actor.name
            viewBinding.actorRole.text = actor.role
            viewBinding.actorSummary.text = actor.summary

            Picasso.get()
                .load(Uri.parse(actor.image))
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_main_noimgfilm)
                .into(viewBinding.actorImage)
        }

        viewModel.bundleMovie.observe(viewLifecycleOwner) { navMovie ->
            if (navMovie != null) {
                val bundle = Bundle()
                bundle.putString(ID, navMovie.id)

                navigateWithBundleID(
                    navMovie.destinationId,
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