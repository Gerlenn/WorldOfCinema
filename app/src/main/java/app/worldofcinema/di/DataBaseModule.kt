package app.worldofcinema.di

import android.content.Context
import app.worldofcinema.data.database.dao.MoviesDAO
import app.worldofcinema.data.database.dao.MoviesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun itemsDatabase(context: Context): MoviesDataBase {
        return MoviesDataBase.getMoviesDatabaseInstance(context)
    }

    @Provides
    fun provideItemsDAO(itemsDataBase: MoviesDataBase): MoviesDAO {
        return itemsDataBase.getMoviesDAO()
    }
}