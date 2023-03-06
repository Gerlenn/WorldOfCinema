package app.worldofcinema.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("FavoritesEntity")
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("imDbRating")
    val imDbRating: String?,
    @ColumnInfo("image")
    val image: String?,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("year")
    val year: String,
)