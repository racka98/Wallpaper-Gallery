package work.racka.wallpapergallery.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import work.racka.wallpapergallery.network.WallpaperProperty

class DetailsViewModel(
    wallpaperProperty: WallpaperProperty,
    app: Application) : AndroidViewModel(app) {

    private val _selectedWallpaperProperty = MutableLiveData<WallpaperProperty>()
    val selectedWallpaperProperty: LiveData<WallpaperProperty> = _selectedWallpaperProperty

    init {
        _selectedWallpaperProperty.value = wallpaperProperty
    }

}
