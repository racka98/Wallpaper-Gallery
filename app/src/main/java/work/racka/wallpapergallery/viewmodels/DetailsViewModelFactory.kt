package work.racka.wallpapergallery.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import work.racka.wallpapergallery.domain.Wallpaper

class DetailsViewModelFactory(
    private val wallpaper: Wallpaper,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(wallpaper, application) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}