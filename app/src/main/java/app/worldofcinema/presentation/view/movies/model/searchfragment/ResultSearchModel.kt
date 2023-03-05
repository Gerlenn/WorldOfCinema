package app.worldofcinema.presentation.view.movies.model.searchfragment

import app.worldofcinema.data.model.searchesponse.Result

data class ResultSearchModel(
    val description: String,
    val image: String,
    val title: String
){
    constructor(result: Result) : this(
        result.description,
        result.image,
        result.title
    )
}