package superheroapp

import com.google.gson.annotations.SerializedName


data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("result") val superheroes: List<SuperheroItemResponse>
)


data class SuperheroItemResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val name: String
)

