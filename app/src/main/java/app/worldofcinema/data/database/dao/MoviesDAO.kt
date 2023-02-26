package app.worldofcinema.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.worldofcinema.data.database.entities.CategoriesEntity
import app.worldofcinema.data.database.entities.MoviesEntity

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
}