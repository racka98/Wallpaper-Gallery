package work.racka.wallpapergallery.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import work.racka.wallpapergallery.domain.Wallpaper

//Room entities for the database
@Entity(tableName = "wallpapers_collection")
data class WallpaperDatabase(
    @PrimaryKey
    val url: String,
    val author: String,
    val collections: String,
    val dimensions: String,
    val downloadable: Boolean,
    val name: String,
    val thumbnail: String
)

//Converts database objects to domain object (Wallpaper)
fun List<WallpaperDatabase>.asDomainModel(): List<Wallpaper> {
    return map {
        Wallpaper(
            url = it.url,
            author = it.author,
            collections = it.collections,
            dimensions = it.dimensions,
            downloadable = it.downloadable,
            name = it.name,
            thumbnail = it.thumbnail
        )
    }
}