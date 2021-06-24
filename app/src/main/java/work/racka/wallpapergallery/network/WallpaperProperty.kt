package work.racka.wallpapergallery.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
//import kotlinx.android.parcel.Parcelize

@Parcelize
data class WallpaperProperty(
    val author: String,
    val collections: String,
    val dimensions: String,
    val downloadable: Boolean,
    val name: String,
    val thumbnail: String,
    val url: String
) : Parcelable
