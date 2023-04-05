package app.worldofcinema.data.service

import app.worldofcinema.data.model.detailsresponse.ActorResponse
import app.worldofcinema.data.model.detailsresponse.DetailsResponse
import app.worldofcinema.data.model.moviesesponse.MoviesResponse
import app.worldofcinema.data.model.searchesponse.SearchResponse
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

    @GET("Title/$API_KEY/{id}/FullActor,Images,Trailer")
    suspend fun getDetailsMovieById(@Path("id") movieId: String): Response<DetailsResponse>

    @GET("Title/$API_KEY/{id}/FullActor")
    suspend fun getAllActorsByMovieTitle(@Path("id") movieTitle: String): Response<DetailsResponse>

    @GET("SearchMovie/$API_KEY/{text}")
    suspend fun searchMovies(@Path("text") searchText: String): Response<SearchResponse>
}

