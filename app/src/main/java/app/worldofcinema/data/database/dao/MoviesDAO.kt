package app.worldofcinema.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.worldofcinema.data.database.entities.CategoriesEntity
import app.worldofcinema.data.database.entities.MoviesEntity

@Dao
interface MoviesDAO {

    @Insert
    fun insertCategoryEntity(categoriesEntity: CategoriesEntity)

    @Insert
    fun insertMoviesEntity(moviesEntity: MoviesEntity)

    @Query("SELECT * FROM CategoriesEntity")
    fun getCategoriesEntities(): List<CategoriesEntity>

    @Query("SELECT * FROM MoviesEntity")
    fun getMoviesEntities(): List<MoviesEntity>
}