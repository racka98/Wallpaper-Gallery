package work.racka.wallpapergallery.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import work.racka.wallpapergallery.network.WallpaperApi
import work.racka.wallpapergallery.network.WallpaperProperty

class MainViewModel : ViewModel() {

    private val _wallpapers = MutableLiveData<List<WallpaperProperty>>()
    val wallpapers: LiveData<List<WallpaperProperty>>
        get() = _wallpapers

    init {
        getWallpaperProperties()
    }

    private fun getWallpaperProperties() {
        viewModelScope.launch {
            try {
                _wallpapers.value = WallpaperApi.wallpaperService.getWallpapers()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Possibly no internet connection", e)
            }
        }
    }
}