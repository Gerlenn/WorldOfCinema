package app.worldofcinema.di

import android.content.Context
import app.worldofcinema.data.auth.AuthRepositoryImpl
import app.worldofcinema.data.movies.MoviesRepositoryImpl
import app.worldofcinema.data.service.ApiService
import app.worldofcinema.data.sharedpreferences.SharedPreferencesHelper
import app.worldofcinema.domain.auth.AuthRepository
import app.worldofcinema.domain.movies.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        private const val BASE_URL = "https://imdb-api.com/en/API/"

        @Provides
        fun provideSharedPreferences(
            @ApplicationContext context: Context,
        ): SharedPreferencesHelper {
            return SharedPreferencesHelper(
                context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)
            )
        }

        @Provides
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }

        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}