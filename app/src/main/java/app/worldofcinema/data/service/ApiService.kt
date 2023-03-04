package app.worldofcinema.data.service

import app.worldofcinema.data.model.detailsfragment.DetailsResponse
import app.worldofcinema.data.model.moviesfragment.MoviesResponse
import app.worldofcinema.utils.AppConstants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("Top250Movies/$API_KEY")
    suspend fun getTopMovies(): Response<MoviesResponse>

    @GET("MostPopularMovies/$API_KEY")
    suspend fun getMostPopularMovies(): Response<MoviesResponse>

    @GET("InTheaters/$API_KEY")
    suspend fun getInTheatersMovies(): Response<MoviesResponse>

    @GET("ComingSoon/$API_KEY")
    suspend fun getComingSoonMovies(): Response<MoviesResponse>

    @GET("Title/$API_KEY/{id}/Trailer")
    suspend fun getDetailsMovieById(@Path("id") movieId: String): Response<DetailsResponse>
}

