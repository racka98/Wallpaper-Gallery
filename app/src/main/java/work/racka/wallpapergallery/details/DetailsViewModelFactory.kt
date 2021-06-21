package work.racka.wallpapergallery.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import work.racka.wallpapergallery.network.WallpaperProperty

class DetailsViewModelFactory(
    private val wallpaperProperty: WallpaperProperty,
    private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(wallpaperProperty, application) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}