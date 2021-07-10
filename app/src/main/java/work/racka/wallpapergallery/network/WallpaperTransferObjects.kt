package work.racka.wallpapergallery.network

import work.racka.wallpapergallery.database.WallpaperDatabase

//import kotlinx.android.parcel.Parcelize

//Represents a single wallpaper from the JSON
data class WallpaperProperty(
    val author: String,
    val collections: String,
    val dimensions: String,
    val downloadable: Boolean,
    val name: String,
    val thumbnail: String,
    val url: String
)

//Converts data transfer objects to database objects
fun List<WallpaperProperty>.asDatabaseModel(): Array<WallpaperDatabase> {
    return map {
        WallpaperDatabase(
            url = it.url,
            author = it.author,
            collections = it.collections,
            dimensions = it.dimensions,
            downloadable = it.downloadable,
            name = it.name,
            thumbnail = it.thumbnail
        )
    }.toTypedArray()
}
