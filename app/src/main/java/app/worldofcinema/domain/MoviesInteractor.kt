package app.worldofcinema.domain

import javax.inject.Inject

class MoviesInteractor @Inject constructor(
    private val moviesRepository: MoviesRepository
    ){

}