package superheroapp

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Part

interface ApiService {

    @GET("api/c9fb57f7ea7b5081e0d3e1153318b830/search/{name}")
    suspend fun getSuperheroes(@Part("name")superheroName:String):Response<SuperHeroDataResponse>
}