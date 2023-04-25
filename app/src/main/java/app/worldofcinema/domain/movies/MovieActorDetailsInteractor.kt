package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.detailsactorfragment.ActorDetailsModel
import javax.inject.Inject

class MovieActorDetailsInteractor @Inject constructor(
    private val movieActorDetailsRepository: MovieActorDetailsRepository
) {

    suspend fun getActorDetailsById(actorId: String): ActorDetailsModel{
        return movieActorDetailsRepository.getActorDetailsById(actorId)
    }

}