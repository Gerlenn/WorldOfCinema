package app.worldofcinema.di

import app.worldofcinema.domain.auth.AuthInteractor
import app.worldofcinema.domain.auth.AuthRepository
import app.worldofcinema.domain.movies.MoviesInteractor
import app.worldofcinema.domain.movies.MoviesRepository
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
}