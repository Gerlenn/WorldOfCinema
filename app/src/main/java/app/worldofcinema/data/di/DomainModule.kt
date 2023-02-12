package app.worldofcinema.data.di

import app.worldofcinema.domain.MoviesInteractor
import app.worldofcinema.domain.MoviesRepository
import app.worldofcinema.domain.auth.AuthInteractor
import app.worldofcinema.domain.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideMoviesInteractor(
        moviesRepository: MoviesRepository
    ): MoviesInteractor {
        return MoviesInteractor(moviesRepository)
    }

    @Provides
    fun provideAuthInteractor(
        authRepository: AuthRepository
    ): AuthInteractor {
        return AuthInteractor(authRepository)
    }
}