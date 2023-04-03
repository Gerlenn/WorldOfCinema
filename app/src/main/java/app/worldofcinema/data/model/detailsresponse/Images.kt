package app.worldofcinema.data.model.detailsresponse

data class Images(
    val errorMessage: String,
    val fullTitle: String,
    val imDbId: String,
    val items: List<ImagesResponse>?,
    val title: String,
    val type: String,
    val year: String,
)