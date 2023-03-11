package app.worldofcinema.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.worldofcinema.data.database.entities.CategoriesEntity
import app.worldofcinema.data.database.entities.FavoritesEntity
import app.worldofcinema.data.database.entities.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategoryEntity(categoriesEntity: CategoriesEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMoviesEntity(moviesEntity: MoviesEntity)

    @Query("SELECT * FROM CategoriesEntity")
    fun getCategoriesEntities(): List<CategoriesEntity>

    @Query("SELECT * FROM MoviesEntity WHERE categoryId=:categoryId")
    fun getMoviesEntities(categoryId: Int): List<MoviesEntity>

    @Query("SELECT(SELECT COUNT(*) FROM MoviesEntity) !=0")
    fun doesMovieEntityExist(): Boolean

    @Query("SELECT(SELECT COUNT(*) FROM CategoriesEntity) !=0")
    fun doesCategoryEntityExist(): Boolean

    @Query("SELECT * FROM MoviesEntity WHERE id = :id")
    fun findMovieEntityById(id: String): MoviesEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoritesEntity(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM FavoritesEntity")
    fun getFavoritesEntities(): Flow<List<FavoritesEntity>>

    @Query("DELETE FROM FavoritesEntity WHERE id = :id")
    fun deleteFavoriteEntityById(id: String)

    @Query("UPDATE MoviesEntity SET isFavorite = :isFavorite WHERE id = :id")
    fun addToFavorite(id: String, isFavorite: Boolean)

    @Query("SELECT * FROM MoviesEntity WHERE id = :id")
    fun getStateFavorite(id: String): MoviesEntity
}