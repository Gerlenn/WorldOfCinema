package app.worldofcinema.presentation.view.movies.model.detailsactorfragment

data class ActorDetailsModel(
    val awards: String?,
    val birthDate: String?,
    val deathDate: String?,
    val image: String,
    val knownFor: List<KnownForModel>?,
    val name: String,
    val role: String?,
    val summary: String
)
