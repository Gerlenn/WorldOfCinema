package app.worldofcinema.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CategoriesEntity")
data class CategoriesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo( "categoryId")
    val categoryId: Int,
    @ColumnInfo( "categoryTitle")
    val categoryTitle: String
)