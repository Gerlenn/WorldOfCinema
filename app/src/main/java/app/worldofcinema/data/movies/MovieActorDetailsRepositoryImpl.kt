package app.worldofcinema.data.movies

import app.worldofcinema.data.service.ApiService
import app.worldofcinema.domain.movies.MovieActorDetailsRepository
import app.worldofcinema.presentation.view.movies.model.detailsactorfragment.ActorDetailsModel
import app.worldofcinema.presentation.view.movies.model.detailsactorfragment.KnownForModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieActorDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : MovieActorDetailsRepository {

    override suspend fun getActorDetailsById(actorId: String): ActorDetailsModel {
        return withContext(Dispatchers.IO) {

            val response = apiService.getDetailsActorById(actorId)

            val detailsActor = response.body()?.let { actor ->
                ActorDetailsModel(
                    actor.awards,
                    actor.birthDate,
                    actor.deathDate,
                    actor.image,
                    actor.knownFor.map { actorInTheMovie ->
                        KnownForModel(
                            actorInTheMovie.id,
                            actorInTheMovie.image,
                            actorInTheMovie.role,
                            actorInTheMovie.title,
                            actorInTheMovie.year
                        )
                    },
                    actor.name,
                    actor.role,
                    actor.summary
                )
            }
            detailsActor!!
        }
    }
}