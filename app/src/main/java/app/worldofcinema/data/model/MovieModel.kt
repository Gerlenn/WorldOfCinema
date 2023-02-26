package app.worldofcinema.data.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("imDbRating")
    val imDbRating: String?,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String,
)