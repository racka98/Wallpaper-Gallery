package work.racka.wallpapergallery.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import work.racka.wallpapergallery.domain.Wallpaper

class DetailsViewModel(
    wallpaper: Wallpaper,
    app: Application
) : AndroidViewModel(app) {

    private val _selectedWallpaperProperty = MutableLiveData<Wallpaper>()
    val selectedWallpaper: LiveData<Wallpaper> = _selectedWallpaperProperty

    init {
        _selectedWallpaperProperty.value = wallpaper
    }

}
