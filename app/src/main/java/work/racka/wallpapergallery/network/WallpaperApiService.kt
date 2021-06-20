package work.racka.wallpapergallery.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/racka98/Pastel-A12-Wallpapers/main/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Creating the retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//Interface to retrieve the required JSON response
interface WallpaperApiService {
    @GET("wallpapers")
    suspend fun getWallpapers(): List<WallpaperProperty>
}

//Exposing the WallpaperApiService to the entire app with a public object
object WallpaperApi {
    val wallpaperService: WallpaperApiService by lazy {
        retrofit.create(WallpaperApiService::class.java)
    }
}
