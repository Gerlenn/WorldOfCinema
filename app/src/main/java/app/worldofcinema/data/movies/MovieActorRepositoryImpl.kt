package app.worldofcinema.data.movies

import app.worldofcinema.data.service.ApiService
import app.worldofcinema.domain.movies.MovieActorRepository
import app.worldofcinema.presentation.view.movies.model.detailsfragment.Actor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieActorRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : MovieActorRepository {

    override suspend fun showActorMovies(movieTitle: String): List<Actor> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getAllActorsByMovieTitle(movieTitle)
            response.body()?.actorList.let { listActors ->
                listActors?.map { actor ->
                    Actor(
                        actor.id,
                        actor.image,
                        actor.name,
                        actor.asCharacter
                    )
                }
            } ?: kotlin.run {
                emptyList()
            }
        }
    }
}