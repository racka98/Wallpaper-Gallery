package work.racka.wallpapergallery.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import work.racka.wallpapergallery.network.WallpaperApi
import work.racka.wallpapergallery.network.WallpaperProperty

enum class WallpaperApiStatus {
    LOADING,
    ERROR,
    DONE
}

class MainViewModel : ViewModel() {

    private val _wallpapers = MutableLiveData<List<WallpaperProperty>>()
    val wallpapers: LiveData<List<WallpaperProperty>>
        get() = _wallpapers

    private val _status = MutableLiveData<WallpaperApiStatus>()
    val status: LiveData<WallpaperApiStatus> = _status

    init {
        getWallpaperProperties()
    }

    private fun getWallpaperProperties() {
        viewModelScope.launch {
            try {
                _status.value = WallpaperApiStatus.LOADING
                _wallpapers.value = WallpaperApi.wallpaperService.getWallpapers()
                _status.value = WallpaperApiStatus.DONE
            } catch (e: Exception) {
                _status.value = WallpaperApiStatus.ERROR
                Log.e("MainViewModel", "Possibly no internet connection", e)
            }
        }
    }
}
