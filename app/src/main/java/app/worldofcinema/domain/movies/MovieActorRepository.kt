package app.worldofcinema.domain.movies

import app.worldofcinema.presentation.view.movies.model.detailsfragment.Actor

interface MovieActorRepository {

    suspend fun showActorMovies(movieTitle: String): List<Actor>
}