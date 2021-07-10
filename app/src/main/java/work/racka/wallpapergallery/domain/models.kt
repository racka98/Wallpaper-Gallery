package work.racka.wallpapergallery.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Domain objects are plain Kotlin data classes that represent the things in our app. These are the
 * objects that should be displayed on screen, or manipulated by the app.
 *
 * see database for objects that are mapped to the database
 * see network for objects that parse or prepare network calls
 */

@Parcelize
data class Wallpaper(
    val author: String,
    val collections: String,
    val dimensions: String,
    val downloadable: Boolean,
    val name: String,
    val thumbnail: String,
    val url: String
) : Parcelable