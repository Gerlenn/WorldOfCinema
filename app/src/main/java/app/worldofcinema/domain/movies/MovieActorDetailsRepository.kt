package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.detailsactorfragment.ActorDetailsModel

interface MovieActorDetailsRepository {

    suspend fun getActorDetailsById(actorId: String): ActorDetailsModel
}