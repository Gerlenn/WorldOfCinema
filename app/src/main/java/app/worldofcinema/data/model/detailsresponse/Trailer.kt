package app.worldofcinema.data.model.detailsresponse

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("linkEmbed")
    val linkEmbed: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
)