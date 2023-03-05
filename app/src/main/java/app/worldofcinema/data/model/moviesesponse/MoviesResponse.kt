package app.worldofcinema.data.model.moviesesponse

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("items")
    val items: List<MovieModel>,
)