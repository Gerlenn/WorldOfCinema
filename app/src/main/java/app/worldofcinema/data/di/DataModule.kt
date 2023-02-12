package app.worldofcinema.data.di

import android.content.Context
import app.worldofcinema.data.movies.MoviesRepositoryImpl
import app.worldofcinema.data.auth.AuthRepositoryImpl
import app.worldofcinema.data.sharedpreferences.SharedPreferencesHelper
import app.worldofcinema.domain.MoviesRepository
import app.worldofcinema.domain.auth.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindMoviesRepository(
        moviesRepositoryImpl: MoviesRepositoryImpl,
    ): MoviesRepository

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    companion object {

        private const val SP_KEY = "SP_KEY"

        @Provides
        fun provideSharedPreferences(
            @ApplicationContext context: Context,
        ): SharedPreferencesHelper {
            return SharedPreferencesHelper(
                context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)
            )
        }
    }
}