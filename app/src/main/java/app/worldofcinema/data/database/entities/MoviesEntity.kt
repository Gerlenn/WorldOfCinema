package app.worldofcinema.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("MoviesEntity")
data class MoviesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo( "categoryId")
    val categoryId: Int,
    @ColumnInfo("movieId")
    val id: Int,
    @ColumnInfo("imDbRating")
    val imDbRating: String,
    @ColumnInfo( "image")
    val image: String,
    @ColumnInfo( "title")
    val title: String,
    @ColumnInfo( "year")
    val year: String
)