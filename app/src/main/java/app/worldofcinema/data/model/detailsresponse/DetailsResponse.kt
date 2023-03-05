package app.worldofcinema.data.model.detailsresponse

import com.google.gson.annotations.SerializedName

data class DetailsResponse(
    @SerializedName("awards")
    val awards: String?,
    @SerializedName("countries")
    val countries: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imDbRating")
    val imDbRating: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("plot")
    val plot: String,
    @SerializedName("stars")
    val stars: String,
    @SerializedName("genres")
    val genres: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("trailer")
    val trailer: Trailer?,
)