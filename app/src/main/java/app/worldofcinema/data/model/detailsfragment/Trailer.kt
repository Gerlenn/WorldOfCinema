package app.worldofcinema.data.model.detailsfragment

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("linkEmbed")
    val linkEmbed: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
)