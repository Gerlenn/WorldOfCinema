package app.worldofcinema.data.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.worldofcinema.data.database.entities.CategoriesEntity
import app.worldofcinema.data.database.entities.FavoritesEntity
import app.worldofcinema.data.database.entities.MoviesEntity

@Database(entities = [MoviesEntity::class, CategoriesEntity::class, FavoritesEntity::class],
    version = 1,
    exportSchema = false)
abstract class MoviesDataBase : RoomDatabase() {

    abstract fun getMoviesDAO(): MoviesDAO

    companion object {
        private const val DATABASE_NAME = "movies_database"
        private var DATABASE_INSTANCE: MoviesDataBase? = null

        fun getMoviesDatabaseInstance(context: Context): MoviesDataBase {
            return DATABASE_INSTANCE ?: Room
                .databaseBuilder(
                    context.applicationContext,
                    MoviesDataBase::class.java,
                    DATABASE_NAME
                )
                .build()
                .also { DATABASE_INSTANCE = it }
        }
    }
}