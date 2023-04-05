package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.detailsfragment.Actor
import javax.inject.Inject

class MovieActorInteractor @Inject constructor(
    private val movieActorRepository: MovieActorRepository,
) {
    suspend fun showActorMovies(movieTitle: String): List<Actor> {
        return movieActorRepository.showActorMovies(movieTitle)
    }
}