package work.racka.wallpapergallery.network

data class WallpaperProperty(
    val author: String,
    val collections: String,
    val dimensions: String,
    val downloadable: Boolean,
    val name: String,
    val thumbnail: String,
    val url: String
)
