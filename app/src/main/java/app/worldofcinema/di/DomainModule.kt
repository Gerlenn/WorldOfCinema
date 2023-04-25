package app.worldofcinema.di

import app.worldofcinema.domain.auth.AuthInteractor
import app.worldofcinema.domain.auth.AuthRepository
import app.worldofcinema.domain.movies.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideMoviesInteractor(
        moviesRepository: MoviesRepository,
    ): MoviesInteractor {
        return MoviesInteractor(moviesRepository)
    }

    @Provides
    fun provideAuthInteractor(
        authRepository: AuthRepository,
    ): AuthInteractor {
        return AuthInteractor(authRepository)
    }

    @Provides
    fun provideMovieDetailsInteractor(
        movieDetailsRepository: MovieDetailsRepository,
    ): MovieDetailsInteractor {
        return MovieDetailsInteractor(movieDetailsRepository)
    }

    @Provides
    fun provideMovieSearchInteractor(
        movieSearchRepository: MovieSearchRepository,
    ): MovieSearchInteractor {
        return MovieSearchInteractor(movieSearchRepository)
    }

    @Provides
    fun provideMovieFavoritesInteractor(
        movieFavoritesRepository: MovieFavoritesRepository,
    ): MovieFavoritesInteractor {
        return MovieFavoritesInteractor(movieFavoritesRepository)
    }

    @Provides
    fun provideMovieCategoryInteractor(
        movieCategoryRepository: MovieCategoryRepository
    ): MovieCategoryInteractor {
        return MovieCategoryInteractor(movieCategoryRepository)
    }

    @Provides
    fun provideMovieActorsInteractor(
        movieActorRepository: MovieActorRepository
    ): MovieActorInteractor {
        return MovieActorInteractor(movieActorRepository)
    }

    @Provides
    fun provideMovieActorDetailsInteractor(
        movieActorDetailsRepository: MovieActorDetailsRepository
    ): MovieActorDetailsInteractor {
        return MovieActorDetailsInteractor(movieActorDetailsRepository)
    }
}