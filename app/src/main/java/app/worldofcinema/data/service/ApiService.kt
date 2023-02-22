package app.worldofcinema.data.service

import app.worldofcinema.data.model.MoviesResponse
import app.worldofcinema.utils.AppConstants.API_KEY
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("Top250Movies/$API_KEY")
    suspend fun getTopMovies(): Response<MoviesResponse>

    @GET("MostPopularMovies/$API_KEY")
    suspend fun getMostPopularMovies(): Response<MoviesResponse>

    @GET("InTheaters/$API_KEY")
    suspend fun getInTheatersMovies(): Response<MoviesResponse>

    @GET("ComingSoon/$API_KEY")
    suspend fun getComingSoonMovies(): Response<MoviesResponse>
}

